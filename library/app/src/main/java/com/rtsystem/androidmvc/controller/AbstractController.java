package com.rtsystem.androidmvc.controller;


import com.rtsystem.androidmvc.controller.annontation.Controller;
import com.rtsystem.androidmvc.router.Parameter;
import com.rtsystem.androidmvc.router.Router;
import com.rtsystem.androidmvc.router.impl.MemoryRouter;
import com.rtsystem.androidmvc.router.impl.SerializableRouter;
import com.rtsystem.androidmvc.view.activity.ActivityView;

import java.util.List;
import java.util.Observable;

/**
 * Class ${NAME} to {SET DESCRIPTION HERE}
 * TODO: Adds doc.
 * Created by rafaeltonholo on 03/01/2017.
 */
public abstract class AbstractController<T> extends Observable implements ControllerContract<T> {
    protected ActivityView<T> view;
    private T model;

    /**
     * Registers a View in the controller with all bundle received in that
     *
     * @param view The view to register
     */
    @Override
    public void register(ActivityView<T> view) {
        getRouter().checkLaunching(getClass());
        final List<Parameter> params = getParameters();
        this.view = view;
        onInitialize(params.toArray(new Parameter[params.size()]));
    }

    /**
     * Access model business to get information needed to update the view
     *
     * @param params Which Parameters that navigation has.
     */
    protected abstract void onInitialize(Parameter... params);

    /**
     * Executed when the Controller receives a message from view.
     *
     * @param action What is the action from this message
     * @param params Which parameters that message has.
     */
    protected abstract void onMessageReceive(String action, Parameter... params);

    /**
     * If the Controller navigates to another view and needs an result back, its will be called.
     */
    @Override
    public void onBackWithResult(int requestCode, int resultCode) {
    }

    /**
     * Send message from view to controller so they can communicate.
     *
     * @param action What is the action from this message
     * @param params Which parameters that message has.
     */
    @Override
    public void sendMessage(String action, Parameter... params) {
        onMessageReceive(action, params);
    }

    /**
     * Notify the view that has changes to update.
     *
     * @param action An action of the update
     * @param params Which parameters that action has.
     */
    protected final void notifyView(final String action, Parameter... params) {
        final Message message = new Message(action);
        message.setParameters(params);
        view.update(this, message);
    }

    /**
     * Route to {@code target} {@link Controller} from actual {@link Controller} without an action.
     *
     * @param target Class instance of the Target {@link Controller}.
     * @param params The parameters that will be passed to target.
     */
    protected final void route(Class<? extends ControllerContract> target, Parameter... params) {
        getRouter().route(target, Router.EMPTY_ACTION, params);
    }

    /**
     * Route to {@code target} {@link Controller} from actual {@link Controller} with an action.
     *
     * @param target Class instance of the Target {@link Controller}.
     * @param action The action that the target {@link Controller} needs to execute.
     * @param params The parameters that will be passed to target.
     */
    protected final void route(Class<? extends ControllerContract> target, String action, Parameter... params) {
        getRouter().route(target, action, params);
    }

    /**
     * Route to {@code target} {@link Controller} from actual {@link Controller} with an action.
     *
     * @param target    Class instance of the Target {@link Controller}.
     * @param action    The action that the target {@link Controller} needs to execute.
     * @param noHistory {@code true} if the target {@link Controller} can't be on back stack.
     * @param params    The parameters that will be passed to target.
     */
    protected final void route(final Class<?> target, final String action, boolean noHistory, Parameter... params) {
        getRouter().route(target, action, noHistory, params);
    }

    /**
     * Goes back to the previous {@link Controller} or exit the application.
     *
     * @param params Parameters to the previous {@link Controller}
     */
    @Override
    public void back(Parameter... params) {
        getRouter().back(params);
    }

    /**
     * Goes back to the previous {@link Controller} or exit the application.
     *
     * @param action An action that needs to be executed when the previous {@link Controller}
     *               came back
     * @param params Parameters to the previous {@link Controller}
     */
    @Override
    public void back(final String action, Parameter... params) {
        getRouter().back(action, params);
    }

    protected final List<Parameter> getParameters() {
        return getRouter().getParameters();
    }

    @Override
    public T model() {
        return model;
    }

    @Override
    public T model(T model) {
        this.model = model;
        return this.model;
    }

    private Router getRouter() {
        Controller annotation = getClass().getAnnotation(Controller.class);
        if (annotation.serializable()) {
            return SerializableRouter.getInstance(); // TODO: 22/03/2017 Create the SerializableRouter
        } else {
            return MemoryRouter.getInstance();
        }
    }
}
