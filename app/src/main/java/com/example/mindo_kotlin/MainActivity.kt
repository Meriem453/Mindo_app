package com.example.mindo_kotlin

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Parcel
import android.os.SystemClock
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mindo_kotlin.databinding.ActivityMainBinding
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet


class MainActivity : AppCompatActivity(),onSaveClicked {
    private lateinit var binding: ActivityMainBinding
    lateinit var c:Context
    val CHANNEL_ID="2003"
lateinit var cladapter:Class_drawer_adapter




    @RequiresApi(Build.VERSION_CODES.O)
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
        val HMadapter = Tasks_adapter(baseContext)

        binding.hwRec.adapter = HMadapter

        //Courses rec

        val CRLayoutManager2: RecyclerView.LayoutManager =
            LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        binding.crRec.layoutManager = CRLayoutManager2
        val CRadapter = Courses_main_adapter_fid(baseContext)
        binding.crRec.adapter = CRadapter
        //drawer layout  listeners


        binding.classesRec.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        cladapter = Class_drawer_adapter(baseContext)
        binding.classesRec.adapter = cladapter

        binding.clsAdd.setOnClickListener(View.OnClickListener {


            val fragement = AddClassFragement(baseContext, this)
            fragement.show(supportFragmentManager, null)


        })

        //create notification channel
        //
        if(Build.VERSION.SDK>=Build.VERSION_CODES.O.toString()){
            val name="2003" as CharSequence
//
            val notif_channel=NotificationChannel(CHANNEL_ID,name,NotificationManager.IMPORTANCE_HIGH)
            notif_channel.description=R.string.channel_description.toString()
            val notif_mnge=getSystemService(NotificationManager::class.java)
            notif_mnge.createNotificationChannel(notif_channel)



            //set the alarm
            val alarm_mngr=getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val intent=Intent(baseContext,Myreceiver::class.java)
            val pnd_intent=PendingIntent.getBroadcast(baseContext,0,intent,0)
            alarm_mngr.setInexactRepeating(
                AlarmManager.ELAPSED_REALTIME_WAKEUP
                ,SystemClock.elapsedRealtime() + AlarmManager.INTERVAL_FIFTEEN_MINUTES
                , AlarmManager.INTERVAL_FIFTEEN_MINUTES
                ,pnd_intent)



        }

       val d= Description()
           d.text="Random vlues"
binding.lineChart2.description=d
val ydata=ArrayList<Entry>()
        val e=Entry()
        e.data=10

        val e1=Entry()
        e1.data=20

        val e2=Entry()
        e2.data=30

ydata.add(e)
        ydata.add(e1)
        ydata.add(e2)

        val xdata=ArrayList<String>()
        xdata.add("nbr1")
        xdata.add("nbr2")
        xdata.add("nbr3")

        val lds=LineDataSet(ydata,"Random Values")
        val ld=LineData(lds)
        binding.lineChart2.data=ld
        binding.lineChart2.invalidate()









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

