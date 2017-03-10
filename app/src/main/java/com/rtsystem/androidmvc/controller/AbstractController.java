package com.rtsystem.androidmvc.controller;


import com.rtsystem.androidmvc.view.activity.ActivityView;

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
     * @param view   The view to register
     * @param params the parameters
     */
    @Override
    public void register(ActivityView<T> view, Parameter... params) {
        this.view = view;
        onInitialize(params);
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
    public void onBackWithResult(int requestCode, int resultCode, Parameter... parameters) {
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

    @Override
    public T model() {
        return model;
    }

    @Override
    public T model(T model) {
        this.model = model;
        return this.model;
    }
}
