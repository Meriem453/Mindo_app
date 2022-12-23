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

class Courses_Mycourses_adapter_fid(var c:Context,var Class:String):
    RecyclerView.Adapter<Courses_Mycourses_adapter_fid.MycoursesHolder>() {

   val db:AppDatabase=AppDatabase.getInstance(c)
    var arr=db.courseDao().loadByClass(Class)
inner class MycoursesHolder(itemView: View) : ViewHolder(itemView){
    val Title:TextView=itemView.findViewById(R.id.mycr_title)
    val Desc:TextView=itemView.findViewById(R.id.mycr_desc)
    val TimeStart:TextView=itemView.findViewById(R.id.mycr_time_start)
    val TimeEnd:TextView=itemView.findViewById(R.id.mycr_time_end)
    val Imp:ProgressBar=itemView.findViewById(R.id.mycr_progress)
    val Alarm:TextView=itemView.findViewById(R.id.mycr_alarm)
    val Delete=itemView.findViewById<ImageView>(R.id.mycr_delete)

}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MycoursesHolder {
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.mycourses_card,parent,false)
        val holder=MycoursesHolder(itemView)
        return holder
    }

    override fun onBindViewHolder(holder: MycoursesHolder, position: Int) {

        with(holder){
            Title.text=arr.get(position).Title
            Desc.text=arr.get(position).Desc
            TimeStart.text=arr.get(position).TimeStart
            TimeEnd.text=arr.get(position).TimeEnd
            Imp.progress=arr.get(position).Imp
            if (arr.get(position).Alarm){
                Alarm.setText(R.string.set)
            }else{
                Alarm.setText(R.string.inset)
            }
           Delete.setOnClickListener(View.OnClickListener {
               db.courseDao().delete(arr.get(position))
               dataChanged()
               Toast.makeText(c,"Course deleted",Toast.LENGTH_SHORT).show()

           })
        }
    }


    override fun getItemCount(): Int {
        return arr.size
    }

    fun dataChanged(){
        arr=db.courseDao().loadByClass(Class)
        notifyDataSetChanged()

    }
}