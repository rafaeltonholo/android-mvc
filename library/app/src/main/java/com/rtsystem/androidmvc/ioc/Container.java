package com.rtsystem.androidmvc.ioc;

import com.rtsystem.androidmvc.logging.LogManager;

import java.util.HashMap;

/**
 * Class Container to {SET DESCRIPTION HERE}
 * <p>
 * Created on 22/03/2017.
 *
 * @author Rafael
 */
public class Container {
    private static final HashMap<Class<?>, Class<?>> sContainerItems;
    private static final HashMap<Class<?>, Object> sContainerInstances;

    static {
        sContainerItems = new HashMap<>();
        sContainerInstances = new HashMap<>();
    }

    public static void register(Class<?> key, Object value) {
        if (sContainerItems.containsKey(key)) {
            LogManager.d(Container.class, "This key already exists.");
            return;
        }

        sContainerItems.put(key, value.getClass());
        sContainerInstances.put(value.getClass(), value);
    }

    public static <T> T resolve(Class<?> key) {
        return resolve(key, false);
    }

    public static <T> T resolve(Class<?> key, boolean force) {
        final Class<?> value = sContainerItems.get(key);
        //noinspection unchecked
        T item = (T) sContainerInstances.get(value);
        if (force && item == null) {
            try {
                item = (T) value.newInstance();
                sContainerInstances.put(value, item);
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return item;
    }

    public static void remove(Class<?> key) {
        sContainerItems.remove(key);
        sContainerInstances.remove(key);
    }
}
