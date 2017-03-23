package com.rtsystem.androidmvc.logging;

import android.util.Log;

/**
 * Class LogManager to {SET DESCRIPTION HERE}
 * TODO: 08/03/2017 adicionar documentação
 *
 * @since 08/03/2017
 * Created by Rafael.
 */
public class LogManager {
    /**
     * Send a {@link Log#VERBOSE} log message.
     *
     * @param who Used to identify the source of a log message.  It usually identifies
     *            the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    public static void v(Object who, String msg) {
        final String tag = (who instanceof Class) ? ((Class) who).getSimpleName() : who.getClass().getSimpleName();
        Log.v(tag, msg);
    }

    /**
     * Send a {@link Log#VERBOSE} log message and log the exception.
     *
     * @param who Used to identify the source of a log message.  It usually identifies
     *            the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     * @param tr  An exception to log
     */
    public static void v(Object who, String msg, Throwable tr) {
        final String tag = (who instanceof Class) ? ((Class) who).getSimpleName() : who.getClass().getSimpleName();
        Log.v(tag, msg, tr);
    }

    /**
     * Send a {@link Log#DEBUG} log message.
     *
     * @param who Used to identify the source of a log message.  It usually identifies
     *            the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    public static void d(Object who, String msg) {
        final String tag = (who instanceof Class) ? ((Class) who).getSimpleName() : who.getClass().getSimpleName();
        Log.d(tag, msg);
    }

    /**
     * Send a {@link Log#DEBUG} log message and log the exception.
     *
     * @param who Used to identify the source of a log message.  It usually identifies
     *            the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     * @param tr  An exception to log
     */
    public static void d(Object who, String msg, Throwable tr) {
        final String tag = (who instanceof Class) ? ((Class) who).getSimpleName() : who.getClass().getSimpleName();
        Log.d(tag, msg, tr);
    }

    /**
     * Send a {@link Log#DEBUG} log message and log the exception.
     *
     * @param who       Used to identify the source of a log message.  It usually identifies
     *                  the class or activity where the log call occurs.
     * @param msg       The message you would like logged.
     * @param showStack show stacktrace
     */
    public static void d(Object who, String msg, boolean showStack) {
        final String tag = (who instanceof Class) ? ((Class) who).getSimpleName() : who.getClass().getSimpleName();
        Log.d(tag, msg);
    }

    /**
     * Send an {@link Log#INFO} log message.
     *
     * @param who Used to identify the source of a log message.  It usually identifies
     *            the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    public static void i(Object who, String msg) {
        final String tag = (who instanceof Class) ? ((Class) who).getSimpleName() : who.getClass().getSimpleName();
        Log.i(tag, msg);
    }

    /**
     * Send a {@link Log#INFO} log message and log the exception.
     *
     * @param who Used to identify the source of a log message.  It usually identifies
     *            the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     * @param tr  An exception to log
     */
    public static void i(Object who, String msg, Throwable tr) {
        final String tag = (who instanceof Class) ? ((Class) who).getSimpleName() : who.getClass().getSimpleName();
        Log.i(tag, msg, tr);
    }

    /**
     * Send a {@link Log#WARN} log message.
     *
     * @param who Used to identify the source of a log message.  It usually identifies
     *            the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    public static void w(Object who, String msg) {
        final String tag = (who instanceof Class) ? ((Class) who).getSimpleName() : who.getClass().getSimpleName();
        Log.w(tag, msg);
    }

    /**
     * Send a {@link Log#WARN} log message and log the exception.
     *
     * @param who Used to identify the source of a log message.  It usually identifies
     *            the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     * @param tr  An exception to log
     */
    public static void w(Object who, String msg, Throwable tr) {
        final String tag = (who instanceof Class) ? ((Class) who).getSimpleName() : who.getClass().getSimpleName();
        Log.w(tag, msg, tr);
    }

    /*
     * Send a {@link Log#WARN} log message and log the exception.
     * @param who Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
     * @param tr An exception to log
     */
    public static void w(Object who, Throwable tr) {
        final String tag = (who instanceof Class) ? ((Class) who).getSimpleName() : who.getClass().getSimpleName();
        Log.w(tag, tr);
    }

    /**
     * Send an {@link Log#ERROR} log message.
     *
     * @param who Used to identify the source of a log message.  It usually identifies
     *            the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    public static void e(Object who, String msg) {
        final String tag = (who instanceof Class) ? ((Class) who).getSimpleName() : who.getClass().getSimpleName();
        Log.e(tag, msg);
    }

    /**
     * Send a {@link Log#ERROR} log message and log the exception.
     *
     * @param who Used to identify the source of a log message.  It usually identifies
     *            the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     * @param tr  An exception to log
     */
    public static void e(Object who, String msg, Throwable tr) {
        final String tag = (who instanceof Class) ? ((Class) who).getSimpleName() : who.getClass().getSimpleName();
        Log.e(tag, msg, tr);
    }
}
