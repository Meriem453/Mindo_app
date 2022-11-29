package com.example.mindo_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mindo_kotlin.databinding.ActivityMyHomeworksBinding

class MyHomeworks : AppCompatActivity(),onSaveClicked{
    private lateinit var bind: ActivityMyHomeworksBinding
lateinit var HMadapter:HomeWorks_Myhomeworks_adapter_fid

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_homeworks)

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


    }

    override fun saveClicked() {
        HMadapter.DataChanged()
    }


}
