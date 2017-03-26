package com.rtsystem.androidmvc.router;

import com.rtsystem.androidmvc.controller.annontation.Controller;

import java.util.List;

/**
 * The Router that will help {@link Controller Controllers} navigate to others
 * {@link Controller Controllers} passing an action to {@link Controller}. It
 * also manages the {@link Controller}'s back stack, that can be util on apps flow.
 *
 * @author Rafael
 * @since 0.0.1
 */
public interface Router {
    String EMPTY_ACTION = "";

    /**
     * Route to {@code target} {@link Controller} from actual {@link Controller} without an action.
     *
     * @param target Class instance of the Target {@link Controller}.
     * @param params The parameters that will be passed to target.
     */
    void route(final Class<?> target, Parameter... params);

    /**
     * Route to {@code target} {@link Controller} from actual {@link Controller} with an action.
     *
     * @param target Class instance of the Target {@link Controller}.
     * @param action The action that the target {@link Controller} needs to execute.
     * @param params The parameters that will be passed to target.
     */
    void route(final Class<?> target, final String action, Parameter... params);

    /**
     * Route to {@code target} {@link Controller} from actual {@link Controller} with an action.
     *
     * @param target    Class instance of the Target {@link Controller}.
     * @param action    The action that the target {@link Controller} needs to execute.
     * @param noHistory {@code true} if the target {@link Controller} can't be on back stack.
     * @param params    The parameters that will be passed to target.
     */
    void route(final Class<?> target, final String action, boolean noHistory, Parameter... params);

    /**
     * Route to {@code target} {@link Controller} from actual {@link Controller} without an action
     * and waiting a result from the other controller when it executes a back action.
     *
     * @param target      Class instance of the Target {@link Controller}.
     * @param requestCode The Requested code from action
     * @param params      The parameters that will be passed to target.
     */
    void routeWithResult(final Class<?> target, final int requestCode, Parameter... params);

    /**
     * Route to {@code target} {@link Controller} from actual {@link Controller} with an action
     * and waiting a result from the other controller when it executes a back action.
     *
     * @param target      Class instance of the Target {@link Controller}.
     * @param action      The action that the target {@link Controller} needs to execute.
     * @param requestCode The Requested code from action
     * @param params      The parameters that will be passed to target.
     */
    void routeWithResult(final Class<?> target, final String action, final int requestCode, Parameter... params);

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
    void routeWithResult(final Class<?> target, final String action, boolean noHistory, final int requestCode, Parameter... params);

    /**
     * Goes back to the previous {@link Controller} or exit the application.
     *
     * @param params Parameters to the previous {@link Controller}
     */
    void back(Parameter... params);

    /**
     * Goes back to the previous {@link Controller} or exit the application.
     *
     * @param action An action that needs to be executed when the previous {@link Controller}
     *               came back
     * @param params Parameters to the previous {@link Controller}
     */
    void back(final String action, Parameter... params);

    void checkLaunching(Class<?> controller);

    List<Parameter> getParameters();
}
