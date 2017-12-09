package com.example.matteotognon.remedio;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
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
        gerarNotificacao(context, intent);
    }


    public void gerarNotificacao(Context context, Intent intent){
        this.context = context;
        PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent,0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setTicker("Aviso de medicamento");
        builder.setContentTitle("Controle de medicamento");
        builder.setContentText("hora de tomar o medicamento agendado");
        builder.setSmallIcon(R.drawable.common_google_signin_btn_icon_dark);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Notification notification = builder.build();
        notification.vibrate = new long[]{150,300,150,600};
        notification.flags = Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(001,notification);

        try {

            Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone toque = RingtoneManager.getRingtone(context, uri);
            toque.play();
        }catch (Exception e){

        }
    }
}
