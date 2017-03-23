package com.rtsystem.androidmvc.view.activity;

import java.util.Observer;

/**
 * Class ${NAME} to {SET DESCRIPTION HERE}
 * <p>
 * TODO: 09/03/2017 Adicionar documentação
 * Created by rafaeltonholo on 03/01/2017.
 */
public interface ActivityView<T> extends Observer {
    T model();

    T model(T model);
}
