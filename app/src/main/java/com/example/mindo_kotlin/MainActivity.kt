package com.example.mindo_kotlin

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mindo_kotlin.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(),onSaveClicked {
    private lateinit var binding: ActivityMainBinding
    lateinit var c:Context
    val CHANNEL_ID="2003"
lateinit var cladapter:Class_drawer_adapter


    //used for register alarm manager
    var pendingIntent: PendingIntent? = null

    //used to store running alarmmanager instance
    var alarmManager: AlarmManager? = null

    //Callback function for Alarmmanager event
    var mReceiver: BroadcastReceiver? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //hide the action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar()?.hide()
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        c = baseContext

        //Homeworks rec


        val HMlayoutManager: RecyclerView.LayoutManager =
            LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        binding.hwRec.setLayoutManager(HMlayoutManager)
        val HMadapter = HomeWorks_main_adapter_fid(baseContext)

        binding.hwRec.adapter = HMadapter

        //Courses rec

        val CRLayoutManager2: RecyclerView.LayoutManager =
            LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        binding.crRec.layoutManager = CRLayoutManager2
        val CRadapter = Courses_main_adapter_fid(baseContext)
        binding.crRec.adapter = CRadapter
        //bottom navigation bare listeners


        binding.classesRec.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        cladapter = Class_drawer_adapter(baseContext)
        binding.classesRec.adapter = cladapter

        binding.clsAdd.setOnClickListener(View.OnClickListener {


            val fragement = BlankFragment(baseContext, this)
            fragement.show(supportFragmentManager, null)


        })

        //set the notifacation


//create a channel
          if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

        val intent = Intent(this, Welcome::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent2: PendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        var builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.logo)
            .setContentTitle("Mindo")
            .setContentText("Good morning!")
            .setStyle(NotificationCompat.BigTextStyle()
                .bigText("You didn't submit your work today."))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent2)
            .setAutoCancel(true)




        //alarm
        val alarmManager =
            c.getSystemService(Context.ALARM_SERVICE) as? AlarmManager
        val pendingIntent =
            PendingIntent.getService(c, 12, intent,
                PendingIntent.FLAG_NO_CREATE)
        if (pendingIntent != null && alarmManager != null) {
            alarmManager.cancel(pendingIntent)
        }
        alarmManager?.setInexactRepeating(
            AlarmManager.ELAPSED_REALTIME_WAKEUP,
            SystemClock.elapsedRealtime() + AlarmManager.INTERVAL_DAY,
            AlarmManager.INTERVAL_HALF_DAY,
            null
        )




    }







    fun notifClick(view: View) {
        val intent=Intent(c,My_notifications::class.java)
        startActivity(intent)

    }

    fun menuClick(view: View) {

        binding.top.open()
    }

    override fun saveClicked() {
        cladapter.edit()
    }

}

