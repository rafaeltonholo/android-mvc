package com.rtsystem.androidmvc.controller;


import com.rtsystem.androidmvc.controller.annontation.Controller;
import com.rtsystem.androidmvc.router.Parameter;
import com.rtsystem.androidmvc.view.activity.ActivityView;

/**
 * Class ${NAME} to {SET DESCRIPTION HERE}
 * TODO: Adds doc.
 * <p>
 * Created by rafaeltonholo on 03/01/2017.
 */
public interface ControllerContract<T> {
    /**
     * Registers a View in the controller with all bundle received in that
     *
     * @param view The view to register
     */
    void register(ActivityView<T> view);

    /**
     * Send message from view to controller so they can communicate.
     *
     * @param action What is the action from this message
     * @param params Which parameters that message has.
     */
    void sendMessage(final String action, Parameter... params);

    /**
     * If the Controller navigates to another view and needs an result back, its will be called.
     */
    void onBackWithResult(int requestCode, int resultCode);

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

    T model();

    T model(T model);
}
