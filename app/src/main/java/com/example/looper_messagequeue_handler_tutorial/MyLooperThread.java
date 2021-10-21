package com.example.looper_messagequeue_handler_tutorial;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

public class MyLooperThread extends Thread{

    private static final String TAG = "MyLooperThread";

    public Handler handler;

    public Handler handler_2nd_way;

    public Looper looper; //Ei Variable diya vahir er kono Class theke Looper ke call kore Thread END korte parbo

    @Override
    public void run() {

        //Looper initilize, Handler Initilize and then infinite Loop... ei ORDER ee CODE likte hobe naile kaaj korbe nah

        Looper.prepare(); //eitar madhome LOOPER create korlam amar nijer BACKGROUND Thread er moddhe...LOOPER er sathe sathe amra EXTRA vabe "MESSAGE_QUEUE" oo initialize korlam

        looper = Looper.myLooper();

        //Normally jei THREAD er moddhe asi sheikane "NEW HANDLER()" call korle oi "BackGround Thread" er jonno Handler create hoye jay
        handler = new Handler(); //kono THREAD ee MESSAGE_QUEUE nah takle sheikane HANDLER create kora possible nah... eijonno agge "LOOPER" agge initialize korte hobe

        handler_2nd_way = new MyHandler();


        Looper.loop(); //INFINITE LOOP... jeitar maadhome BACKGROUND Thread ta ALLTIME chalo takbe

        Log.d(TAG, "End of run()");
    }
}
