package gothadubai.incubasys.com.gothadubai;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import CommonUtils.CommonVaraibles;

/**
 * Created by Xain on 07/11/2016.
 */

public class GothaFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d("GothaFMService", "From: " + remoteMessage.getFrom());
        Log.d("GothaFMService", "From: " + remoteMessage.getData().get("title"));
        Log.d("GothaFMService", "From: " + remoteMessage.getData().get("message"));

        showNotification(remoteMessage.getData().get(CommonVaraibles.CONSTANT_PARAM_TITLE), remoteMessage.getData().get(CommonVaraibles.CONSTANT_PARAM_MESSAGE));

    }

    private void showNotification(String title, String msg){
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setSmallIcon(R.mipmap.ic_launcher);
        mBuilder.setContentTitle(title);
        mBuilder.setContentText(msg);

        Intent notificationIntent = new Intent(this, SplashActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(contentIntent);


        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, mBuilder.build());
    }
}
