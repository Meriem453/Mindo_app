package com.example.mindo_kotlin

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class Class_drawer_adapter (var c:Context): RecyclerView.Adapter<Class_drawer_adapter.ClassHolder>() {

    val db=AppDatabase.getInstance(c)
    var arr=db.classDao().getAll()
  inner class ClassHolder(itemView: View) : ViewHolder(itemView){
      val text=itemView.findViewById<TextView>(R.id.clstext)
      //val img=itemView.findViewById<ImageView>(R.id.clsimg)


  }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassHolder {
        val itemView=LayoutInflater.from(c).inflate(R.layout.text,parent,false)


        return ClassHolder(itemView)
    }

    override fun onBindViewHolder(holder: ClassHolder, position: Int) {

       holder.text.text=arr.get(position).cls
        holder.itemView.setOnClickListener {
            var intent=Intent(c,Class_items::class.java)
            intent.putExtra("Class",arr.get(position).cls)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            c.startActivity(intent)


        }
    }

    override fun getItemCount(): Int {

        return arr.size
    }
fun edit(){
    arr=db.classDao().getAll()
    notifyDataSetChanged()
}

}
