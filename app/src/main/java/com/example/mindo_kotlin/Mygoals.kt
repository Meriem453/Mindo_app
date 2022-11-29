package com.example.mindo_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mindo_kotlin.databinding.ActivityMygoalsBinding

class Mygoals : AppCompatActivity() {
    lateinit var binding:ActivityMygoalsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mygoals)
        binding=ActivityMygoalsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val arr=ArrayList<String>()
        arr.add("Have the complete mark at SFSD")
        arr.add("Finish all the homeworks at 6 pm")
        val adapter=Goals_mygoals_adapter_fid(arr)
        val manager:RecyclerView.LayoutManager=LinearLayoutManager(this,RecyclerView.VERTICAL,false)
        binding.goalRec.adapter=adapter
        binding.goalRec.layoutManager=manager


    }
}