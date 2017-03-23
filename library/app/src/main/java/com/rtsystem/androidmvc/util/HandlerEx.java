package com.rtsystem.androidmvc.util;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/**
 * Class ${NAME} to {SET DESCRIPTION HERE}
 * TODO: 09/03/2017 Adicionar documentação
 * <p>
 * Created by rafaeltonholo on 05/01/2017.
 */
public class HandlerEx extends Handler {
    public static final int RESULT_MESSAGE_OK = 1000000;
    public static final int RESULT_MESSAGE_FAIL = 1000001;

    private final OnHandleMessageListener mOnHandleMessageListener;

    public HandlerEx(OnHandleMessageListener onHandleMessageListener) {
        super();
        mOnHandleMessageListener = onHandleMessageListener;
    }

    public HandlerEx(Callback callback, OnHandleMessageListener onHandleMessageListener) {
        super(callback);
        mOnHandleMessageListener = onHandleMessageListener;
    }

    public HandlerEx(Looper looper, OnHandleMessageListener onHandleMessageListener) {
        super(looper);
        mOnHandleMessageListener = onHandleMessageListener;
    }

    public HandlerEx(Looper looper, Callback callback, OnHandleMessageListener onHandleMessageListener) {
        super(looper, callback);
        mOnHandleMessageListener = onHandleMessageListener;
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        if (mOnHandleMessageListener != null) {
            mOnHandleMessageListener.onHandleMessage(msg);
        }
    }

    public interface OnHandleMessageListener {
        void onHandleMessage(Message message);
    }
}