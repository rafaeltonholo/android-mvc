package com.rtsystem.androidmvc.router.impl;

import android.app.Activity;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.util.Size;
import android.util.SizeF;

import com.rtsystem.androidmvc.controller.annontation.Controller;
import com.rtsystem.androidmvc.ioc.Container;
import com.rtsystem.androidmvc.router.Parameter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class SerializableRouter to {SET DESCRIPTION HERE}
 * <p>
 * Created on 22/03/2017.
 *
 * @author Rafael
 */
public class SerializableRouter extends AbstractRouter {
    private static SerializableRouter sInstance;
    private Intent mIntent;

    private SerializableRouter() {
        super();
    }

    /**
     * A Singleton Instance from this class
     */
    public static SerializableRouter getInstance() {
        synchronized (SerializableRouter.class) {
            if (sInstance == null) {
                sInstance = new SerializableRouter();
            }

            return sInstance;
        }
    }

    @Override
    public void route(Class<?> target, String action, boolean noHistory, Parameter... params) {
        final Activity view = Container.resolve(actualController);
        final Controller annotation = target.getAnnotation(Controller.class);
        if (annotation == null) {
            throw new RuntimeException("The target controller needs to annotate");
        }

        addParameters(params);
        final Class<?> clazz = annotation.value();
        mIntent = new Intent(view, clazz);
        mIntent.putExtras(createParameters());
        view.startActivity(mIntent);
        actualController = target;
        pushBackStack(target);
    }

    @Override
    public void back(String action, Parameter... params) {

    }

    private Bundle createParameters() {
        final Bundle bundle = new Bundle();
        for (Parameter parameter : super.getParameters()) {
            final String key = parameter.getKey();
            final Object value = parameter.getValue();

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                if (value instanceof Size) {
                    bundle.putSize(key, (Size) value);
                } else if (value instanceof SizeF) {
                    bundle.putSizeF(key, (SizeF) value);
                }
            } else if (value instanceof Boolean) {
                bundle.putBoolean(key, (Boolean) value);
            } else if (value instanceof byte[]) {
                bundle.putByteArray(key, (byte[]) value);
            } else if (value instanceof short[]) {
                bundle.putShortArray(key, (short[]) value);
            } else if (value instanceof char[]) {
                bundle.putCharArray(key, (char[]) value);
            } else if (value instanceof float[]) {
                bundle.putFloatArray(key, (float[]) value);
            } else if (value instanceof String[]) {
                bundle.putStringArray(key, (String[]) value);
            } else if (value instanceof CharSequence[]) {
                bundle.putCharSequenceArray(key, (CharSequence[]) value);
            } else if (value instanceof Bundle) {
                bundle.putBundle(key, (Bundle) value);
            } else if (value instanceof Integer) {
                bundle.putInt(key, (Integer) value);
            } else if (value instanceof Long) {
                bundle.putLong(key, (Long) value);
            } else if (value instanceof Double) {
                bundle.putDouble(key, (Double) value);
            } else if (value instanceof String) {
                bundle.putString(key, (String) value);
            } else if (value instanceof boolean[]) {
                bundle.putBooleanArray(key, (boolean[]) value);
            } else if (value instanceof int[]) {
                bundle.putIntArray(key, (int[]) value);
            } else if (value instanceof long[]) {
                bundle.putLongArray(key, (long[]) value);
            } else if (value instanceof double[]) {
                bundle.putDoubleArray(key, (double[]) value);
            } else if (value instanceof Byte) {
                bundle.putByte(key, (Byte) value);
            } else if (value instanceof Character) {
                bundle.putChar(key, (Character) value);
            } else if (value instanceof Short) {
                bundle.putShort(key, (Short) value);
            } else if (value instanceof Float) {
                bundle.putFloat(key, (Float) value);
            } else if (value instanceof CharSequence) {
                bundle.putCharSequence(key, (CharSequence) value);
            } else if (value instanceof Parcelable) {
                bundle.putParcelable(key, (Parcelable) value);
            } else if (value instanceof Parcelable[]) {
                bundle.putParcelableArray(key, (Parcelable[]) value);
            } else if (value instanceof Serializable) {
                bundle.putSerializable(key, (Serializable) value);
            } else if (value instanceof Binder) {
                bundle.putBinder(key, (Binder) value);
            }
        }

        clearParameters();
        return bundle;
    }

    @Override
    public List<Parameter> getParameters() {
        return getParameters(mIntent);
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
}
