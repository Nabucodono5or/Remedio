package com.example.matteotognon.remedio;


import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

/**
 * Created by daenerys on 12/8/17.
 */

public class BroadcastReceiverAux extends BroadcastReceiver {
    private static final String TAG = "notification";
    Context context;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e(TAG, "onReceive");
        gerarNotificacao(context);
    }


    public void gerarNotificacao(Context context){
        this.context = context;

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setTicker("texto rápido de apresentação");
        builder.setContentTitle("titulo");
        builder.setContentText("descrição");
        builder.setSmallIcon(R.drawable.common_google_signin_btn_icon_dark);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(001,builder.build());


    }
}
