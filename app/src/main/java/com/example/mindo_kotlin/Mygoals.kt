package com.example.mindo_kotlin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mindo_kotlin.databinding.ActivityMygoalsBinding

class Mygoals : AppCompatActivity() {
    lateinit var binding:ActivityMygoalsBinding
    lateinit var c:Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mygoals)

        c=baseContext

        binding=ActivityMygoalsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val adapter=Goals_mygoals_adapter_fid(baseContext)
        val manager:RecyclerView.LayoutManager=LinearLayoutManager(this,RecyclerView.VERTICAL,false)
        binding.goalRec.adapter=adapter
        binding.goalRec.layoutManager=manager
        binding.bottomNavigation.inflateMenu(R.menu.bottom_navigation_menu)
        binding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){

                R.id.page_homeworks -> run {

                    val intent= Intent(c,MyHomeworks::class.java)

                    startActivity(intent)


                    true
                }
                R.id.page_home -> {
                    val intent= Intent(c,MainActivity::class.java)
                    startActivity(intent)

                    true
                }
                R.id.page_courses -> {
                    val intent= Intent(c,MyCourses::class.java)

                    startActivity(intent)

                    true
                }
                R.id.page_schedule -> {


                    true
                }
                R.id.page_goals -> {
                    val intent= Intent(c,Mygoals::class.java)
                    startActivity(intent)

                    true
                }
                else -> false
            }
        }


    }
}