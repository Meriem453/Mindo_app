package com.example.mindo_kotlin

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteConstraintException
import android.net.Uri.Builder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.DialogFragment
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


        binding.classesRec.layoutManager=LinearLayoutManager(this,RecyclerView.VERTICAL,false)
        binding.classesRec.adapter=Class_drawer_adapter(baseContext)

binding.clsAdd.setOnClickListener (View.OnClickListener {


val fragement=BlankFragment(baseContext)
    fragement.show(supportFragmentManager,null)








})
}







    fun notifClick(view: View) {
        val intent=Intent(c,My_notifications::class.java)
        startActivity(intent)

    }

    fun menuClick(view: View) {

        binding.top.open()
    }

}

