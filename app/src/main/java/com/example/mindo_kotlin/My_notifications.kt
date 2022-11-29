package com.example.mindo_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mindo_kotlin.databinding.ActivityMyNotificationsBinding

class My_notifications : AppCompatActivity() {
    lateinit var binding:ActivityMyNotificationsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_notifications)

        binding=ActivityMyNotificationsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        var arr=ArrayList<String>()
        arr.add("You have a new course")
        arr.add("You didn't acheive your goal yet")

            val l_manager:RecyclerView.LayoutManager=LinearLayoutManager(this,RecyclerView.VERTICAL,false)
            val l_adapter=Notif_Mynotif_adapter_fid(arr)

            binding.notifRec.layoutManager=l_manager
            binding.notifRec.adapter=l_adapter


        }
    }
