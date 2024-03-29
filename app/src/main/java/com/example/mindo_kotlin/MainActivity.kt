package com.example.mindo_kotlin

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mindo_kotlin.databinding.ActivityMainBinding
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class MainActivity : AppCompatActivity(),onSaveClicked {
    private lateinit var binding: ActivityMainBinding
    lateinit var c:Context
    val CHANNEL_ID="2003"
lateinit var cladapter:Class_drawer_adapter
lateinit var clRecAdapter: clRecAdapter




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




        //Tasks rec


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
        cladapter = Class_drawer_adapter(baseContext,this)
        binding.classesRec.adapter = cladapter

        binding.clsAdd.setOnClickListener(View.OnClickListener {


            val fragement = AddClassFragement(baseContext, this)
            fragement.show(supportFragmentManager, null)


        })
        //Classes rec
        clRecAdapter=clRecAdapter(baseContext)
        binding.clRec.adapter=clRecAdapter
        binding.clRec.layoutManager=LinearLayoutManager(this,RecyclerView.HORIZONTAL,false)
        //create notification channel

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
//Line Chart Settings
       val d= Description()
           d.text="My progress"
binding.lineChart2.description=d
val ydata=ArrayList<Entry>()
        val e=Entry(0f,15f)
        val e1=Entry(1f,20f)
        val e3=Entry(2f,10f)
        val e4=Entry(3f,11f)
        val e5=Entry(4f,13f)
        val e6=Entry(5f,10f)
        val e7=Entry(6f,15f)
        val e8=Entry(7f,17f)
        val e9=Entry(8f,10f)
        val e10=Entry(9f,14f)

        ydata.add(e)
        ydata.add(e1)
        ydata.add(e3)
        ydata.add(e4)
        ydata.add(e5)
        ydata.add(e6)
        ydata.add(e7)
        ydata.add(e8)
        ydata.add(e9)
        ydata.add(e10)



        val lds=LineDataSet(ydata," My progress (Tasks + Goals) ")
        lds.lineWidth = 5f
        lds.valueTextSize = 10f
        lds.mode = LineDataSet.Mode.HORIZONTAL_BEZIER
        lds.color = ContextCompat.getColor(this, R.color.main_red)
        lds.valueTextColor = ContextCompat.getColor(this, R.color.main_red)



        val ld=LineData(lds)
        ld.setValueTextSize(15f)
        ld.setValueTextColor(R.color.main_blue)

        with(binding.lineChart2){
            animateX(1200, Easing.EaseInExpo)
            description.isEnabled = false

            xAxis.setDrawGridLines(false)

            xAxis.position = XAxis.XAxisPosition.BOTTOM
            //xAxis.granularity = 1F
            xAxis.valueFormatter = MyAxisFormatter()

            axisRight.isEnabled = false
            extraRightOffset = 30f

            legend.orientation = Legend.LegendOrientation.VERTICAL
            legend.verticalAlignment = Legend.LegendVerticalAlignment.TOP
            legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
            legend.textSize = 15F
            legend.form = Legend.LegendForm.EMPTY

        }
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
        clRecAdapter.edit()
    }

    override fun displayDialog(position:Int) {
        MaterialAlertDialogBuilder(c,R.style.MaterialAlertDialog_App)
            .setTitle("Delete")
            .setMessage("Are you sure you want to delete this class ?")
            .setNeutralButton("Cancel"){dialog , which ->
                dialog.dismiss()
            }
            .setNegativeButton("No") { dialog, which ->
                dialog.dismiss()
            }
            .setPositiveButton("Yes") { dialog, which ->
               cladapter.delete(position)
                saveClicked()
            }
            .show()

    }


        inner class MyAxisFormatter : IndexAxisValueFormatter() {

            private var items = arrayListOf("Sat", "Sun", "Mon","Tus","Wen","Thu","Fri","Sat","Sun","Mon")

            override fun getAxisLabel(value: Float, axis: AxisBase?): String? {
                val index = value.toInt()
                return if (index < items.size) {

                    items[index]
                } else {
                    null
                }
            }
        }


    }

