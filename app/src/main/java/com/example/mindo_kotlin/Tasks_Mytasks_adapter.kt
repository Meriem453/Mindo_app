package com.example.mindo_kotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class Tasks_Mytasks_adapter(var c:Context,var Class:String) :
    RecyclerView.Adapter<Tasks_Mytasks_adapter.MyhomeworksAdapter>() {
     var db=AppDatabase.getInstance(c)
    var arr=db.homeworkDao().loadByClass(Class)

    inner class MyhomeworksAdapter (itemView: View): ViewHolder(itemView){
        val myHmTitle:TextView=itemView.findViewById(R.id.myhm_title)
        val myHmDesc:TextView=itemView.findViewById(R.id.myhm_desc)
        val myHmDiff:ProgressBar=itemView.findViewById(R.id.myhm_progress1)
        val myHmImp:ProgressBar=itemView.findViewById(R.id.myhm_progress2)
        val myHmedit=itemView.findViewById<ImageView>(R.id.myhm_edit)
        val myHmdelete=itemView.findViewById<ImageView>(R.id.myhm_delete)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyhomeworksAdapter {
        val itemView:View=LayoutInflater.from(parent.context).inflate(R.layout.mytasks_card,parent,false)
        val holder=MyhomeworksAdapter(itemView)
        return holder

    }

    override fun onBindViewHolder(holder: MyhomeworksAdapter, position: Int) {
        db=AppDatabase.getInstance(c)
        arr=db.homeworkDao().getAll()
        with(holder){
            myHmTitle.text=arr.get(position).Title
            myHmDesc.text=arr.get(position).Desc
            myHmDiff.progress=arr.get(position).Diff
            myHmImp.progress=arr.get(position).Imp
            myHmdelete.setOnClickListener(View.OnClickListener {


                db.homeworkDao().delete(arr.get(position))


                DataChanged()
                Toast.makeText(c,"Homework deleted",Toast.LENGTH_SHORT).show()

            })

        }
    }

    override fun getItemCount(): Int {

       return arr.size
    }

    fun DataChanged(){
        notifyDataSetChanged()
        arr=db.homeworkDao().loadByClass(Class)

    }
}