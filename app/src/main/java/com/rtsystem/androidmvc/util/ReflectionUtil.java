package com.rtsystem.androidmvc.util;

import android.content.Context;
import android.content.pm.PackageManager;

import com.rtsystem.androidmvc.controller.annontation.Controller;
import com.rtsystem.androidmvc.helper.MultiDexHelper;
import com.rtsystem.androidmvc.logging.LogManager;
import com.rtsystem.androidmvc.view.activity.ActivityView;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import dalvik.system.PathClassLoader;

/**
 * Class ReflectionUtil to {SET DESCRIPTION HERE}
 * TODO: 09/03/2017 Adicionar documentação
 *
 * @since 08/03/2017
 * Created by Rafael.
 */
public class ReflectionUtil {
    public static List<Class<?>> getAllClassesByAnnotation(Context context, Class<? extends Annotation> annotation) {
        final List<Class<?>> classes = new ArrayList<>();
        try {
            List<String> allClasses = MultiDexHelper.getAllClasses(context);
            final PathClassLoader classLoader = (PathClassLoader) context.getClassLoader();
            for (String className : allClasses) {
                if (className.contains(context.getPackageName())) {
                    // TODO: 08/03/2017 Verificar o quão caro é fazer o load de cada classe
                    final Class clazz = Class.forName(className, true, classLoader);
                    if (clazz.isAnnotationPresent(annotation) && !Modifier.isAbstract(clazz.getModifiers())) {
                        LogManager.d(ReflectionUtil.class, String.format("Discovered %s annotated with %s", clazz.getSimpleName(),
                                annotation.getSimpleName()));
                        classes.add(clazz);
                    }
                }
            }
        } catch (PackageManager.NameNotFoundException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return classes;
    }

    public static Object getControllerFromView(Context context, Class<? extends ActivityView> activityViewClass) {
        final List<Class<?>> classes = ReflectionUtil.getAllClassesByAnnotation(context, Controller.class);

        Object controller = null;
        for (Class<?> clazz : classes) {
            Controller annotation = clazz.getAnnotation(Controller.class);
            if (annotation.value() == activityViewClass) {
                try {
                    controller = clazz.newInstance();
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return controller;
    }
}
