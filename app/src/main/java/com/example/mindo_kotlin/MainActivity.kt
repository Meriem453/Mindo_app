package com.example.mindo_kotlin

import android.content.Context
import android.content.Intent
import android.net.Uri.Builder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mindo_kotlin.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var c:Context


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //hide the action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar()?.hide()
        }

        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        c=baseContext

        //Homeworks rec




        val HMlayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this,RecyclerView.HORIZONTAL,false)
        binding.hwRec.setLayoutManager(HMlayoutManager)
        val HMadapter=HomeWorks_main_adapter_fid(baseContext)

        binding.hwRec.adapter=HMadapter

        //Courses rec

        val CRLayoutManager2:RecyclerView.LayoutManager=LinearLayoutManager(this,RecyclerView.HORIZONTAL,false)
        binding.crRec.layoutManager=CRLayoutManager2
        val CRadapter=Courses_main_adapter_fid(baseContext)
        binding.crRec.adapter=CRadapter
        //bottom navigation bare listeners
          binding.bottomNavigation.inflateMenu(R.menu.bottom_navigation_menu)
        binding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){

                R.id.page_homeworks -> run {

                    val intent=Intent(c,MyHomeworks::class.java)

                    startActivity(intent)


                    true
                }
                R.id.page_home -> {
                    val intent=Intent(c,MainActivity::class.java)
                    startActivity(intent)

                    true
                }
                R.id.page_courses -> {
                    val intent=Intent(c,MyCourses::class.java)

                    startActivity(intent)

                    true
                }
                R.id.page_schedule -> {


                    true
                }
                R.id.page_goals -> {
                    val intent=Intent(c,Mygoals::class.java)
                    startActivity(intent)

                    true
                }
                else -> false
            }
        }





    }





    fun notifClick(view: View) {
        val intent=Intent(c,My_notifications::class.java)
        startActivity(intent)

    }

}

