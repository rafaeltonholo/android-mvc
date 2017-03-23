package com.rtsystem.androidmvc.router;

/**
 * Class ${NAME} to {SET DESCRIPTION HERE}
 * <p>
 * TODO: Adds doc.
 * Created by rafaeltonholo on 03/01/2017.
 */
public class Parameter {
    private final String mKey;
    private Object mValue;

    public Parameter(String key, Object value) {
        this.mKey = key;
        this.mValue = value;
    }

    public String getKey() {
        return mKey;
    }

    public Object getValue() {
        return mValue;
    }
}
