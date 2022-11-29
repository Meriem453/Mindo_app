package com.example.mindo_kotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class Notif_Mynotif_adapter_fid(var arr:ArrayList<String>) : RecyclerView.Adapter<Notif_Mynotif_adapter_fid.NotifHolder>() {
    inner class NotifHolder(itemView: View): ViewHolder(itemView){
        val text:TextView=itemView.findViewById(R.id.tv_notif)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotifHolder {

        val itemView:View=LayoutInflater.from(parent.context).inflate(R.layout.mynotif_card,parent,false)
        val holder=NotifHolder(itemView)
        return holder
    }

    override fun onBindViewHolder(holder: NotifHolder, position: Int) {
        holder.text.text=arr.get(position)
        /*if(arr.get(position)=="Course"){
            holder.text.setDrawableStart(R.drawable.library)
        }else{
        }*/
    }

    override fun getItemCount(): Int {
        return arr.size
    }
}