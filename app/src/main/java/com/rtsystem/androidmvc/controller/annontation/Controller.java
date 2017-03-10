package com.rtsystem.androidmvc.controller.annontation;

import com.rtsystem.androidmvc.view.activity.ActivityView;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to indicate the class as a Controller.
 * It defines too which view the Controller has.
 * <p>
 * TODO: 09/03/2017 Adds a processor to annotations to avoid Reflection
 * Created by Rafael on 08/03/2017.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Controller {
    Class<? extends ActivityView> value();
}
