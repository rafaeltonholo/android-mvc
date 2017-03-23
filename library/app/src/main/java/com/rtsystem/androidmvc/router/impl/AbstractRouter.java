package com.rtsystem.androidmvc.router.impl;

import com.rtsystem.androidmvc.controller.annontation.Controller;
import com.rtsystem.androidmvc.router.Parameter;
import com.rtsystem.androidmvc.router.Router;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Class AbstractRouter to {SET DESCRIPTION HERE}
 * <p>
 * Created on 13/03/2017.
 * TODO: Adds doc
 *
 * @author Rafael
 * @since 0.0.1
 */
public abstract class AbstractRouter implements Router {
    private final Stack<Class<?>> mControllerBackStack;
    private final List<Parameter> mParameters;
    protected Class<?> actualController;
    private Class<?> mOrigin;

    public AbstractRouter() {
        mControllerBackStack = new Stack<>();
        mParameters = new ArrayList<>();
    }

    @Override
    public void checkLaunching(Class<?> controller) {
        if (actualController == null) {
            actualController = controller;
            pushBackStack(actualController);
        }
    }

    /**
     * Route to {@code target} {@link Controller} from actual {@link Controller} without an action.
     *
     * @param target Class instance of the Target {@link Controller}.
     * @param params The parameters that will be passed to target.
     */
    @Override
    public void route(Class<?> target, Parameter... params) {
        route(target, EMPTY_ACTION, params);
    }

    /**
     * Route to {@code target} {@link Controller} from actual {@link Controller} with an action.
     *
     * @param target Class instance of the Target {@link Controller}.
     * @param action The action that the target {@link Controller} needs to execute.
     * @param params The parameters that will be passed to target.
     */
    @Override
    public void route(Class<?> target, String action, Parameter... params) {
        route(target, action, false, params);
    }

    /**
     * Goes back to the previous {@link Controller} or exit the application.
     *
     * @param params Parameters to the previous {@link Controller}
     */
    @Override
    public void back(Parameter... params) {
        back(EMPTY_ACTION);
    }

    @Override
    public List<Parameter> getParameters() {
        return mParameters;
    }

    public Class<?> getOrigin() {
        return mOrigin;
    }

    protected void pushBackStack(Class<?> clazz) {
        final Controller controller = clazz.getAnnotation(Controller.class);
        if (controller == null) {
            throw new IllegalArgumentException("The class needs to be annotated as Controller");
        }

        Class<?> origin = mControllerBackStack.isEmpty() ? null : mControllerBackStack.peek();
        if (origin != null) {
            mOrigin = origin;
        }

        mControllerBackStack.push(clazz);
    }

    protected Class<?> popBackStack() {
        return mControllerBackStack.pop();
    }

    protected void addParameters(Parameter... parameters) {
        mParameters.addAll(Arrays.asList(parameters));
    }

    protected void clearParameters() {
        mParameters.clear();
    }
}
