package com.example.mindo_kotlin

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_courses)
        binding=ActivityMyCoursesBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val CRLayoutManager: RecyclerView.LayoutManager=
            LinearLayoutManager(this, RecyclerView.VERTICAL,false)
         CRadapter=Courses_Mycourses_adapter_fid(baseContext)

        binding.mycrCrRec.layoutManager=CRLayoutManager
        binding.mycrCrRec.adapter=CRadapter

        var add_course_fragement=Add_course(baseContext,this)


        binding.floatingActionButton2.setOnClickListener(View.OnClickListener {

            add_course_fragement.show(supportFragmentManager,null)

        })




    }

    override fun saveClicked() {
        CRadapter.dataChanged()
    }


}