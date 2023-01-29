package com.example.mindo_kotlin

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class clRecAdapter(var c:Context) : RecyclerView.Adapter<clRecAdapter.clRecholder>() {

    val db=AppDatabase.getInstance(c)
    var arr=db.classDao().getAll()

    inner class clRecholder(itemView: View) : ViewHolder(itemView){
        val text=itemView.findViewById<TextView>(R.id.clRec_txt)
    }








    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): clRecholder {
        val itemView=LayoutInflater.from(c).inflate(R.layout.cl_rec,parent,false)

        return clRecholder(itemView)
    }

    override fun onBindViewHolder(holder: clRecholder, position: Int) {
       holder.text.text=arr.get(position).cls
        holder.itemView.setOnClickListener(View.OnClickListener {
            var intent= Intent(c,Class_items::class.java)
            intent.putExtra("Class",arr.get(position).cls)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            c.startActivity(intent)
        })

    }

    override fun getItemCount(): Int {
      return arr.size
    }
    fun edit(){
        arr=db.classDao().getAll()
        notifyDataSetChanged()
    }
}