package com.rtsystem.testapp.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

/**
 * Created by rafaeltonholo on 02/01/2017.
 */

public final class JsonParser {
    public static <T> T toObject(String json, Class<T> clazz) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        return gson.fromJson(json, clazz);
    }

    public static <T> T toObject(String json, Type clazz) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        return gson.fromJson(json, clazz);
    }
}
