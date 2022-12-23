package com.example.mindo_kotlin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mindo_kotlin.databinding.ActivityClassItemsBinding

class Class_items : AppCompatActivity() {
    lateinit var c:Context
    lateinit var bind:ActivityClassItemsBinding
    lateinit var Class:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_class_items)
//
        c=baseContext
        //hide the action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar()?.hide()
        }


        bind=ActivityClassItemsBinding.inflate(layoutInflater)
        setContentView(bind.root)

        if(intent.extras!=null){
            Class= intent.getStringExtra("Class").toString()


        }
        bind.toolbarText?.text=Class

        bind.classCourses?.setOnClickListener {
            intent= Intent(c,MyCourses::class.java)
            intent.putExtra("Class",Class)
            startActivity(intent)
        }

        bind.classHomeworks?.setOnClickListener {
            intent=Intent(c,MyTasks::class.java)
            intent.putExtra("Class",Class)
            startActivity(intent)
        }



        bind.classGoals?.setOnClickListener {
            intent=Intent(c,Mygoals::class.java)
            intent.putExtra("Class",Class)
            startActivity(intent)
        }



    }
}