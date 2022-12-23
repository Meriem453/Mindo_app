package com.example.mindo_kotlin

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.mindo_kotlin.Class


class AddClassFragement (var c:Context, var onSaveClicked: onSaveClicked): DialogFragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val db=AppDatabase.getInstance(c)
        var v=inflater.inflate(R.layout.add_class_fragment, container, false)
        val add=v.findViewById<TextView>(R.id.fradd_add)
        val edit=v.findViewById<EditText>(R.id.fradd_edit)
        val cancel=v.findViewById<TextView>(R.id.fradd_cancel)

        add.setOnClickListener {
            try {
                db.classDao().insert(com.example.mindo_kotlin.Class(edit.text.toString()))

                onSaveClicked.saveClicked()
                this.dismiss()
            }catch (e:Exception){
                Toast.makeText(c,"This class already exists",Toast.LENGTH_SHORT).show()
            }
        }
cancel.setOnClickListener {
    this.dismiss()
}

        return v
    }



    }
