package com.example.mindo_kotlin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mindo_kotlin.databinding.ActivityMyHomeworksBinding

class MyHomeworks : AppCompatActivity(),onSaveClicked{
    private lateinit var bind: ActivityMyHomeworksBinding
lateinit var HMadapter:HomeWorks_Myhomeworks_adapter_fid
lateinit var c:Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_homeworks)

        c=baseContext

        bind=ActivityMyHomeworksBinding.inflate(layoutInflater)
        setContentView(bind.root)



        val f_manager=supportFragmentManager
        val f_add_homework=Add_homework(baseContext,this)




        val HMLayoutManager: RecyclerView.LayoutManager=
            LinearLayoutManager(this, RecyclerView.VERTICAL,false)
         HMadapter=HomeWorks_Myhomeworks_adapter_fid(baseContext)


        bind.myhmHmRec.layoutManager=HMLayoutManager
        bind.myhmHmRec.adapter=HMadapter


        bind.floatingActionButton.setOnClickListener(View.OnClickListener {

            f_add_homework.show(f_manager,null)

        })

        bind.bottomNavigation.inflateMenu(R.menu.bottom_navigation_menu)
        bind.bottomNavigation.setOnNavigationItemSelectedListener { item ->
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

    override fun saveClicked() {
        HMadapter.DataChanged()
    }


}
