package com.example.mindo_kotlin

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mindo_kotlin.databinding.ActivityMyTasksBinding

class MyTasks : AppCompatActivity(),onSaveClicked{
    private lateinit var bind: ActivityMyTasksBinding
lateinit var HMadapter:Tasks_Mytasks_adapter
lateinit var c:Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_tasks)

        c=baseContext

        bind=ActivityMyTasksBinding.inflate(layoutInflater)
        setContentView(bind.root)

val Class=intent.getStringExtra("Class").toString()

        val f_manager=supportFragmentManager
        val f_add_homework=AddTask(baseContext,this,Class)




        val HMLayoutManager: RecyclerView.LayoutManager=
            LinearLayoutManager(this, RecyclerView.VERTICAL,false)
         HMadapter=Tasks_Mytasks_adapter(baseContext,Class)


        bind.myhmHmRec.layoutManager=HMLayoutManager
        bind.myhmHmRec.adapter=HMadapter


        bind.floatingActionButton.setOnClickListener(View.OnClickListener {

            f_add_homework.show(f_manager,null)

        })



    }

    override fun saveClicked() {
        HMadapter.DataChanged()
    }

    override fun displayDialog(position: Int) {

    }


}
