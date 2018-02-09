package com.example.student.a2018020801;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

import java.util.concurrent.Delayed;

public class MyService extends Service {
    String idLove = "LOVE";
    final int NOTIFICATION_ID = 321321;
    NotificationChannel channelLove;
    NotificationManager nm;

    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= 26)
        {
            regChannel();
        }



        new Thread(){//不new他(Thread)的話，在執行期間按返回鍵退出可能會當機
            //new Thread()可同時舉行其他Thread
            //寫new Thread()關掉程式會重跑
            @Override
            public void run() {
                super.run();
                for (int i=0;i<10;i++) {
                    try {
                        Thread.sleep(1000);
                        Log.d("onCreate", "onCreate==" + i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                sentNotification();
            }
        }.start();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        for (int i=20;i>0;i--) {
//            try {
//                Thread.sleep(1000);
//                Log.d("onStartCommand", "onStartCommand==" +i);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//
//            }
//        }
        new Thread(){
        public void run() {
            super.run();
            for (int i=10;i>0;i--) {
                try {
                    Thread.sleep(1000);
                    Log.d("onStartCommand", "onStartCommand==" + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            sentNotification();
        }
    }.start();
        return super.onStartCommand(intent, flags, startId);

}
    @TargetApi(Build.VERSION_CODES.O)
    @SuppressWarnings("deprecation")
    private void sentNotification()
    {
        Notification.Builder builder;
        if (Build.VERSION.SDK_INT >= 26)
        {
            builder = new Notification.Builder(MyService.this, idLove);
        }
        else
        {
            builder = new Notification.Builder(MyService.this);
        }

//            Intent it = new Intent(MyService.this, InfoActivity.class);
//            it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP |
//                    Intent.FLAG_ACTIVITY_SINGLE_TOP);
//            PendingIntent pi = PendingIntent.getActivity(MyService.this, 123, it,
//                    PendingIntent.FLAG_UPDATE_CURRENT);

        builder.setContentTitle("測試");
        builder.setContentText("這是內容");
        if (Build.VERSION.SDK_INT >= 26)
        {
            builder.setSmallIcon(R.drawable.ic_launcher_foreground);
        }
        else
        {
            builder.setSmallIcon(R.mipmap.ic_launcher);
        }
        builder.setAutoCancel(true);
//            builder.setContentIntent(pi);

        Notification notify = builder.build();
        nm.notify(NOTIFICATION_ID, notify);

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @TargetApi(Build.VERSION_CODES.O)//NotificationChannel必用
    public void regChannel()
    {
        channelLove = new NotificationChannel(
                idLove,
                "Channel Love",
                NotificationManager.IMPORTANCE_HIGH);
        channelLove.setDescription("最重要的人");
        channelLove.enableLights(true);
        channelLove.enableVibration(true);

        nm.createNotificationChannel(channelLove);
    }

}
