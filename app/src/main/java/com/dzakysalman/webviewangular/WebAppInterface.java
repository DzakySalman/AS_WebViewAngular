package com.dzakysalman.webviewangular;

import static android.content.Context.NOTIFICATION_SERVICE;
import static androidx.core.content.ContextCompat.getSystemService;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.icu.text.CaseMap;
import android.icu.text.CaseMap.Title;
import android.content.BroadcastReceiver;
import android.media.RingtoneManager;
import android.os.Build;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.content.Context;
import android.content.Intent;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import org.w3c.dom.Text;
public class WebAppInterface
{
    private Activity _activity;
    private Context _context;

    public WebAppInterface(Context context, Activity activity)
    {
        _context = context;
        _activity = activity;
    }

    @JavascriptInterface
    public void showNotification(String title, String message) {
        NotificationChannel channel = new NotificationChannel("yyChannel", "YY", NotificationManager.IMPORTANCE_DEFAULT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(_context.getApplicationContext())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setChannelId(channel.getId());

        NotificationManager manager = (NotificationManager) _context.getSystemService(NOTIFICATION_SERVICE);

        manager.createNotificationChannel(channel);
        manager.notify(1, builder.build());
    }

    @JavascriptInterface
    public void showCall() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        _context.startActivity(intent);
    }

    @JavascriptInterface
    public void showWhatsApp() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.setType("text/plain");
        sendIntent.setPackage("com.whatsapp");
        _context.startActivity(sendIntent);
    }

    @JavascriptInterface
    public void showCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        _context.startActivity(intent);
    }
}
