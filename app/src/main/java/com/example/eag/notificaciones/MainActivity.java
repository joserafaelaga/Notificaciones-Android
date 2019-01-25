package com.example.eag.notificaciones;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    //Clase para acceder al servicio de notificaciones de Android

    NotificationManager mNotificationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Accedemos al servicio de notificaciones de Android
        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

    }

    protected void onResume(){
        super.onResume();

        //Para hacer desaparecer la notificación cuando se abra la aplicación
        //Ya sea desde el icono o desde la propia notificación
        //mNotificationManager.cancel(01);
    }

    public void notificar(View view){
        //Creamos notificación

        Notification.Builder mBuilder = new Notification.Builder(MainActivity.this);


        //Añadimos icono a la notificación (icono de nuestra app normalmente)
        mBuilder.setSmallIcon(android.R.drawable.stat_sys_warning);


        //Añadimos título a la notificación
        mBuilder.setContentTitle("Nuestra primera notificación");

        //mBuilder.setTicker("Hola");

        //Añadimos texto a la notificación
        mBuilder.setContentText("Texto para la notificación");

        //Añadimos imagen a la notificación, pero tenemos que convertirla a Bitmap
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        mBuilder.setLargeIcon(bmp);

        //Para hacer desaparecer la notificación cuando se pulse sobre esta y se abra la Activity de destino
        mBuilder.setAutoCancel(true);

        //Sonido notificación por defecto
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        mBuilder.setSound(alarmSound);

        //Para que vibre el dispositivo
        mBuilder.setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 });

        //Para barra de progreso (true en movimiento y false con el estado en que se encuentra el progreso)
        mBuilder.setProgress(100,50,true);


        //Abrimos una activity al pulsar sobra la notificación


        //Creamos Intent
        Intent notIntent = new Intent(MainActivity.this, MainActivity.class);

        //Creamos PendingIntent al cual le pasamos nuestro Intent
        PendingIntent contIntent = PendingIntent.getActivity(MainActivity.this, 0,notIntent,0);

        //Añadimos nuestra PendingIntent a la notificación
        mBuilder.setContentIntent(contIntent);


        //Lanzamos la notificación

        mNotificationManager.notify(01, mBuilder.build());

        //Más metodos: https://developer.android.com/reference/android/app/Notification.Builder
    }
}
