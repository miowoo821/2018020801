package com.example.student.a2018020801;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.concurrent.Delayed;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        for (int i=0;i<30;i++) {


            try {
                Thread.sleep(1000);
                Log.d("onCreate", "onCreate==" + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        for (int i=0;i<30;i++) {
            try {
                Thread.sleep(1000);
                Log.d("onStartCommand", "onStartCommand==" + 1);
            } catch (InterruptedException e) {
                e.printStackTrace();

            }
        }


        return super.onStartCommand(intent, flags, startId);






    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
