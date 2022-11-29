package com.example.mindo_kotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class Courses_main_adapter_fid(var c:Context) : RecyclerView.Adapter<Courses_main_adapter_fid.CoursesHolder>() {
val db=AppDatabase.getInstance(c)
    var arr=db.courseDao().getAll()
    inner class CoursesHolder(itemView: View) : ViewHolder(itemView){
        val Title:TextView=itemView.findViewById(R.id.cr_title)
        val Desc:TextView=itemView.findViewById(R.id.cr_desc)
        val TimeStart:TextView=itemView.findViewById(R.id.time_start)
        val TimeEnd:TextView=itemView.findViewById(R.id.time_end)
        val dismiss:TextView=itemView.findViewById(R.id.cr_dismiss)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoursesHolder {

        val itemView:View=LayoutInflater.from(parent.context).inflate(R.layout.main_course_card,parent,false)
        val holder=CoursesHolder(itemView)
        return holder

    }

    override fun onBindViewHolder(holder: CoursesHolder, position: Int) {
        with(holder){

            Title.text=arr.get(position).Title
            Desc.text=arr.get(position).Desc
            TimeStart.text=arr.get(position).TimeStart
            TimeEnd.text=arr.get(position).TimeEnd
            dismiss.setOnClickListener(View.OnClickListener {
                db.courseDao().delete(arr.get(position))
                dataChanged()
            })

        }
    }

    override fun getItemCount(): Int {

      return arr.size
    }

    fun dataChanged(){
        arr=db.courseDao().getAll()
        notifyDataSetChanged()
    }
}