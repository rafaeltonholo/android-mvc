package com.rtsystem.androidmvc.controller;


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
     * @param view   The view to register
     * @param params the parameters
     */
    void register(ActivityView<T> view, Parameter... params);

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
    void onBackWithResult(int requestCode, int resultCode, Parameter... parameters);

    T model();

    T model(T model);
}
