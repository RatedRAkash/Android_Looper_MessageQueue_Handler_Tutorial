package com.example.looper_messagequeue_handler_tutorial;

import static com.example.looper_messagequeue_handler_tutorial.MyHandler.TASK_B;
import static com.example.looper_messagequeue_handler_tutorial.MyHandler.TASK_C;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;

import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private MyLooperThread looperThread = new MyLooperThread();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startThread(View view){
        looperThread.start();
        Log.d(TAG, "New BackGround Thread Started");
    }

    public void stopThread(View view){
        looperThread.looper.quit(); // Background Thread QUIT nah kore ACTIVITY Destroy korleo Background THREAD cholte takbe and ek porjai CRASH khabe
    }

    public void taskA(View view){

        //Normally jei THREAD er moddhe asi sheikane "NEW HANDLER()" call korle oi "BackGround Thread" er jonno Handler create hoye jay
        //but jei THREAD ee asi sheita baade onno THREAD er Handler create korar jonno oi "NEW HANDLER()" korar somoy "looper" er PARAMETER pass kore dile hoye jay
        //Handler threadHandler = new Handler(looperThread.looper);

        //Here we will SEND TASK from MAIN Thread to the BackGround Thread's "MESSAGE_QUEUE"
        looperThread.handler.post(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    Log.d(TAG, "run: "+ i);
                    SystemClock.sleep(1000); //Eitar kaaj same as THREAD.sleep() er moto... khali Try/Catch Block dewa lagge nah
                }
            }
        });

        
    }

    //2nd Way of Executing a Background Thread... instead of using "Runnable" we use "Message" to send it in "Message_QUEUE"
    public void taskB(View view){
        Message msg = Message.obtain(); // Message gula ono boroto RECYCLE hote takhe INFINITE LOOP er moddhe... eri modhhokar ekta  Message Recycled hoye amader Pass kore dibe ".obtain()" call korle
        msg.what = TASK_B;
        looperThread.handler_2nd_way.sendMessage(msg);
    }

    public void taskC(View view){
        Message msg = Message.obtain();
        msg.what = TASK_C;
        looperThread.handler_2nd_way.sendMessage(msg);
    }
}