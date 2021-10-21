package com.example.looper_messagequeue_handler_tutorial;


import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

public class MyHandler extends Handler {

    private static final String TAG = "MyHandler";

    public static final int TASK_B = 2;
    public static final int TASK_C = 3;

    @Override
    public void handleMessage(@NonNull Message msg) {
        switch (msg.what){
            case TASK_B:
                Log.d(TAG, "Task B executed");
                break;

            case TASK_C:
                Log.d(TAG, "Task C executed");
                break;
        }
    }
}

