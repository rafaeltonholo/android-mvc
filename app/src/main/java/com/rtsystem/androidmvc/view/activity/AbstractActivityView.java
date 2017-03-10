package com.rtsystem.androidmvc.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.rtsystem.androidmvc.controller.ControllerContract;
import com.rtsystem.androidmvc.controller.Parameter;
import com.rtsystem.androidmvc.controller.annontation.Controller;
import com.rtsystem.androidmvc.logging.LogManager;
import com.rtsystem.androidmvc.util.ReflectionUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Class ${NAME} to {SET DESCRIPTION HERE}
 * TODO: 09/03/2017 Adicionar documentação
 *
 * @since 03/01/2017
 * Created by rafaeltonholo.
 */
public abstract class AbstractActivityView<T> extends AppCompatActivity implements ActivityView<T> {
    private T model;
    private ControllerContract<T> mController;

    @Override
    @SuppressWarnings("unchecked")
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final List<Parameter> parameters = getParameters(getIntent());
        // TODO: 09/03/2017 Criar annotation processor para evitar reflection
        try {
            mController = (ControllerContract<T>) ReflectionUtil.getControllerFromView(this, getClass());
        } catch (ClassCastException e) {
            LogManager.e(this, "A Controller must implements a ControllerContract");
        }

        mController.register(this, parameters.toArray(new Parameter[parameters.size()]));
    }

    /**
     * Notify the {@link Controller} with a message that has actions to do.
     *
     * @param action The action that {@link Controller} needs to execute.
     * @param params Which parameters that action has.
     */
    protected final void notifyController(String action, Parameter... params) {
        mController.sendMessage(action, params);
    }

    /**
     * Dispatch incoming result to the correct fragment.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        final List<Parameter> parameters = getParameters(data);
        mController.onBackWithResult(requestCode, resultCode, parameters.toArray(new Parameter[parameters.size()]));
    }

    @NonNull
    private List<Parameter> getParameters(Intent intent) {
        final List<Parameter> parameters = new ArrayList<>();
        if (intent != null) {
            Bundle extras = intent.getExtras();
            if (extras != null && !extras.isEmpty()) {
                for (String key : extras.keySet()) {
                    parameters.add(new Parameter(key, extras.get(key)));
                }
            }
        }
        return parameters;
    }

    @Override
    public T model(T model) {
        return mController.model(model);
    }

    @Override
    public T model() {
        return mController.model();
    }
}
