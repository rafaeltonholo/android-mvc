package com.rtsystem.androidmvc.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.rtsystem.androidmvc.controller.ControllerContract;
import com.rtsystem.androidmvc.controller.annontation.Controller;
import com.rtsystem.androidmvc.ioc.Container;
import com.rtsystem.androidmvc.logging.LogManager;
import com.rtsystem.androidmvc.router.Parameter;
import com.rtsystem.androidmvc.util.ReflectionUtil;

/**
 * Class ${NAME} to {SET DESCRIPTION HERE}
 * TODO: 09/03/2017 Adicionar documentação
 *
 * @since 0.0.1
 */
public abstract class AbstractActivityView<T> extends AppCompatActivity implements ActivityView<T> {
    private ControllerContract<T> mController;

    @Override
    @SuppressWarnings("unchecked")
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: 09/03/2017 Criar annotation processor para evitar reflection
        try {
            mController = (ControllerContract<T>) ReflectionUtil.getControllerFromView(this, getClass());
            Container.register(mController.getClass(), this);
        } catch (ClassCastException e) {
            LogManager.e(this, "A Controller must implements a ControllerContract");
        }

        mController.register(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Take care of popping the fragment back stack or finishing the activity
     * as appropriate.
     */
    @Override
    public void onBackPressed() {
        mController.back();
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
        mController.onBackWithResult(requestCode, resultCode);
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
