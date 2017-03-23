package com.rtsystem.androidmvc.controller;

import com.rtsystem.androidmvc.router.Parameter;

import java.util.HashMap;
import java.util.Map;

/**
 * Class ${NAME} to {SET DESCRIPTION HERE}
 * <p>
 * TODO: Adds doc.
 * Created by rafaeltonholo on 03/01/2017.
 * @since 0.0.1
 */
public final class Message {
    private String mAction;
    private Map<String, Object> mParameters;

    public Message(String action) {
        this.mAction = action;
        mParameters = new HashMap<>();
    }

    public String getAction() {
        return mAction;
    }

    public Map<String, Object> getParameters() {
        return mParameters;
    }

    public void setParameters(Parameter... params) {
        for (Parameter param : params) {
            mParameters.put(param.getKey(), param.getValue());
        }
    }

    public Object get(final String key) {
        return mParameters.get(key);
    }

    public Object remove(final String key) {
        return mParameters.remove(key);
    }

    @Override
    public String toString() {
        return "Message{" +
                "mAction='" + mAction + '\'' +
                ", mParameters=" + mParameters +
                '}';
    }
}
