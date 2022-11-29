package com.example.mindo_kotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class HomeWorks_main_adapter_fid(c:Context):
    RecyclerView.Adapter<HomeWorks_main_adapter_fid.HomeworksHolder>() {
val db=AppDatabase.getInstance(c)
    var arr=db.homeworkDao().getAll()

    inner class HomeworksHolder(itemView: View) : ViewHolder(itemView){
        val title:TextView= itemView.findViewById(R.id.hm_title)
        val Desc:TextView=itemView.findViewById(R.id.hm_desc)
        val prog:ProgressBar=itemView.findViewById(R.id.hm_progress)
        val dismiss:TextView=itemView.findViewById(R.id.hm_dismiss)
        val done:TextView=itemView.findViewById(R.id.hm_done)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeworksHolder {

        var view=LayoutInflater.from(parent.context).inflate(R.layout.main_homework_card,parent,false)
        var holder=HomeworksHolder(view)
        return holder

    }

    override fun onBindViewHolder(holder: HomeworksHolder, position: Int) {

        holder.title.text=arr.get(position).Title
        holder.Desc.text=arr.get(position).Desc
        holder.prog.progress=arr.get(position).Diff
        holder.dismiss.setOnClickListener(View.OnClickListener {
            db.homeworkDao().delete(arr.get(position))
            dataChanged()

        })
        holder.done.setOnClickListener(View.OnClickListener {
            db.homeworkDao().delete(arr.get(position))
            dataChanged()

        })
    }

    override fun getItemCount(): Int {

        return  arr.size
    }

    fun dataChanged(){
        arr=db.homeworkDao().getAll()
        notifyDataSetChanged()
    }


}