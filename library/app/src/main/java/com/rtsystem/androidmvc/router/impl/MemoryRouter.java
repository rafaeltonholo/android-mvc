package com.rtsystem.androidmvc.router.impl;

import android.app.Activity;
import android.content.Intent;

import com.rtsystem.androidmvc.controller.annontation.Controller;
import com.rtsystem.androidmvc.ioc.Container;
import com.rtsystem.androidmvc.router.Parameter;

/**
 * Class MemoryRouter to {SET DESCRIPTION HERE}
 * <p>
 * Created on 13/03/2017.
 *
 * @author Rafael
 * @since 0.0.1
 */
public class MemoryRouter extends AbstractRouter {
    private static MemoryRouter sInstance;

    private MemoryRouter() {
        super();
    }

    /**
     * A Singleton Instance from this class
     */
    public static MemoryRouter getInstance() {
        synchronized (MemoryRouter.class) {
            if (sInstance == null) {
                sInstance = new MemoryRouter();
            }

            return sInstance;
        }
    }

    /**
     * Route to {@code target} {@link Controller} from actual {@link Controller} with an action.
     *
     * @param target    Class instance of the Target {@link Controller}.
     * @param action    The action that the target {@link Controller} needs to execute.
     * @param noHistory {@code true} if the target {@link Controller} can't be on back stack.
     * @param params    The parameters that will be passed to target.
     */
    @Override
    public void route(Class<?> target, String action, boolean noHistory, Parameter... params) {
        clearParameters();
        final Activity view = Container.resolve(actualController);
        final Controller annotation = target.getAnnotation(Controller.class);
        if (annotation == null) {
            throw new RuntimeException("The target controller needs to annotate");
        }

        addParameters(params);
        final Class<?> clazz = annotation.value();
        final Intent intent = new Intent(view, clazz);
        view.startActivity(intent);
        actualController = target;
        addParameters(params);
        pushBackStack(target);
    }

    /**
     * Route to {@code target} {@link Controller} from actual {@link Controller} with an action
     * and waiting a result from the other controller when it executes a back action.
     *
     * @param target      Class instance of the Target {@link Controller}.
     * @param action      The action that the target {@link Controller} needs to execute.
     * @param noHistory   {@code true} if the target {@link Controller} can't be on back stack.
     * @param requestCode The Requested code from action
     * @param params      The parameters that will be passed to target.
     */
    @Override
    public void routeWithResult(Class<?> target, String action, boolean noHistory, int requestCode, Parameter... params) {
        if (((requestCode & 0XFFFF0000) != 0)) { // Limitation from FragmentActivity.
            throw new IllegalArgumentException("Can only use lower 16 bits for requestCode");
        }

        clearParameters();
        final Activity view = Container.resolve(actualController);
        final Controller annotation = target.getAnnotation(Controller.class);
        if (annotation == null) {
            throw new NullPointerException("The target controller needs to annotate");
        }
        if (view == null) {
            throw new NullPointerException("The View cannot be null. Controller Name: " + target.getSimpleName());
        }

        addParameters(params);
        final Class<?> clazz = annotation.value();
        final Intent intent = new Intent(view, clazz);
        view.startActivityForResult(intent, requestCode);
        actualController = target;
        addParameters(params);
        pushBackStack(target);
    }

    /**
     * Goes back to the previous {@link Controller} or exit the application.
     *
     * @param action An action that needs to be executed when the previous {@link Controller}
     *               came back
     * @param params Parameters to the previous {@link Controller}
     */
    @Override
    public void back(String action, Parameter... params) {
        clearParameters();
        addParameters(params);
        final Activity view = Container.resolve(actualController);
        view.finish();
        Container.remove(actualController);
        actualController = popBackStack();
    }
}
