package com.rtsystem.testapp.view;

import android.content.Context;

import java.util.Observer;

/**
 * Created by rafaeltonholo on 03/01/2017.
 */

public interface ActivityView<T> extends Observer {
    void setModel(T model);
    Context getContext();
}
