package com.example.mindo_kotlin

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class Myreceiver(var c:Context,var intent :Intent) : BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        val tnt=Intent(c,Welcome::class.java)
        //
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK )
        //
        val pnd=PendingIntent.getActivity(c,0,tnt,0)


        val builder=NotificationCompat.Builder(c,"2003")
            .setSmallIcon(R.drawable.logo).setContentTitle("Mindo")
            .setContentText("Submit your pregress now")
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pnd)


        val notif_mngr=NotificationManagerCompat.from(c)
        notif_mngr.notify(123,builder.build())

    }
}