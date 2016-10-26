package com.archa.archamessenger.Utils;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.PowerManager;
import android.support.v4.app.NotificationCompat;

import com.archa.archamessenger.LoginActivity;
import com.archa.archamessenger.MainActivity;
import com.archa.archamessenger.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by Boo on 2016-06-22.
 */
public class FireBaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "FirebaseMsgService";
    // [START receive_message]
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        //추가한것
        sendPushNotification(remoteMessage);
    }
    private void sendPushNotification(RemoteMessage remoteMessage) {
        String title = remoteMessage.getData().get("title");
        String message = remoteMessage.getData().get("message");
        System.out.println("received message : " + message);
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.icon).setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.icon) )
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setSound(defaultSoundUri).setLights(000000255,500,2000)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        PowerManager pm = (PowerManager) this.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wakelock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, "TAG");
        wakelock.acquire(5000);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }

/*    private void sendNotification(RemoteMessage remoteMessage) {
        String title = remoteMessage.getData().get("title");
        String messageBody = remoteMessage.getData().get("message");
        String mCount = remoteMessage.getData().get("badge");
        String scheme = remoteMessage.getData().get("url");
        String largeIcon = remoteMessage.getData().get("icon");
        String pushType = remoteMessage.getData().get("ptype");
        *//**
        * @param pushType 0: 이벤트 / 1: 댓글 / 2: 소환
        * *//*
        Global_.getInstance().appLog("push type", "type : "+pushType);
        Intent intent;

        Global_.getInstance().appLog("push url", scheme  + " / " +Global_.getInstance().isApplicationBroughtToBackground(this));
        if(! Global_.getInstance().isApplicationBroughtToBackground(this)){
            intent = new Intent(this, MainActivity_.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            intent.putExtra("url", scheme);
        } else {
            intent = new Intent(this, SplashActivity_.class);
            intent.putExtra("url", scheme);
        }
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0 *//* Request code *//*, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        //PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 *//* Request code *//*, intent, PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Bitmap orgImage =
                BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        if(largeIcon != null && !largeIcon.equals("")) {
            largeIcon = ServerConn.getDisplayUrl(largeIcon, false);
            HttpURLConnection connection = null;
            try {
                URL url = new URL(largeIcon);
                connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                orgImage = BitmapFactory.decodeStream(input);

                orgImage = Global_.getInstance().scaleCenterCrop(orgImage, Global_.getInstance().getLengthFromDip(50), Global_.getInstance().getLengthFromDip(50));
                orgImage = Global_.getInstance().circleBitmap(orgImage);

            } catch (Exception e) {
                e.printStackTrace();
            }finally{
                if(connection!=null)connection.disconnect();
            }
        }
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setLargeIcon(orgImage)
                .setSmallIcon(R.drawable.noti)
                .setContentTitle(title)
                .setContentText(messageBody)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(messageBody))
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setColor(getResources().getColor(R.color.tabberPoint))
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_MAX);
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        int notiID = 0;
        if(pushType!=null && pushType!= "") {
            notiID = Integer.parseInt(pushType);
        }
        notificationManager.notify(notiID *//* ID of notification *//*, notificationBuilder.build());

        Global_.getInstance().setBadge(this, Integer.parseInt(mCount));
    }*/


}
