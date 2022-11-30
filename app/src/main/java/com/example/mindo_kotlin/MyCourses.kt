package com.example.mindo_kotlin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mindo_kotlin.databinding.ActivityMyCoursesBinding

class MyCourses : AppCompatActivity(),onSaveClicked {
    lateinit var binding:ActivityMyCoursesBinding
 lateinit var CRadapter:Courses_Mycourses_adapter_fid
lateinit var c:Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_courses)
        binding=ActivityMyCoursesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        c=baseContext

        val CRLayoutManager: RecyclerView.LayoutManager=
            LinearLayoutManager(this, RecyclerView.VERTICAL,false)
         CRadapter=Courses_Mycourses_adapter_fid(baseContext)

        binding.mycrCrRec.layoutManager=CRLayoutManager
        binding.mycrCrRec.adapter=CRadapter

        var add_course_fragement=Add_course(baseContext,this)


        binding.floatingActionButton2.setOnClickListener(View.OnClickListener {

            add_course_fragement.show(supportFragmentManager,null)

        })
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

    override fun saveClicked() {
        CRadapter.dataChanged()
    }


}