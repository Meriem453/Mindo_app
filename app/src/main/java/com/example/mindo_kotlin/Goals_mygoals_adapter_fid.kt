package com.example.mindo_kotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class Goals_mygoals_adapter_fid(var arr:ArrayList<String>): RecyclerView.Adapter<Goals_mygoals_adapter_fid.GoalsHolder>() {
    inner class GoalsHolder(itemView: View): ViewHolder(itemView){
        val tv:TextView=itemView.findViewById(R.id.tv_goal)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoalsHolder {
        val itemView:View=LayoutInflater.from(parent.context).inflate(R.layout.mygoals_card,parent,false)
        val holder=GoalsHolder(itemView)
    return holder
    }

    override fun onBindViewHolder(holder: GoalsHolder, position: Int) {
       holder.tv.text=arr.get(position)
    }

    override fun getItemCount(): Int {
       return arr.size
    }
}