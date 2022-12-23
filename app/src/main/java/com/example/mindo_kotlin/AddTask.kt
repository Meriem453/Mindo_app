package com.example.mindo_kotlin


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RadioGroup
import androidx.fragment.app.DialogFragment
import com.example.mindo_kotlin.databinding.FragmentAddTaskBinding
import com.google.android.material.textfield.TextInputEditText


class AddTask(var c:Context,var onSaveClicked: onSaveClicked,var Class:String): DialogFragment() {
    lateinit var _binding:FragmentAddTaskBinding
    lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL,R.style.hm_dialog)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        lateinit var view:View
        view = inflater.inflate(R.layout.fragment_add_task,container,false)
        val save=view.findViewById<ImageView>(R.id.addhomework_save)
        val close=view.findViewById<ImageView>(R.id.addhomework_close)
        val title=view.findViewById<TextInputEditText>(R.id.addhomexork_title)
        val desc=view.findViewById<TextInputEditText>(R.id.addhomework_desc)
        val imp=view.findViewById<RadioGroup>(R.id.addhomework_imp)
        val diff=view.findViewById<RadioGroup>(R.id.addhomework_diff)

        var Imp=0
        imp.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { radioGroup, i ->
            when(i){
                R.id.addhomework_low ->{
                    Imp=1
                }
                R.id.addhomework_medium->{
                    Imp=2
                }
                R.id.addhomework_high->{
                    Imp=3
                }
                else->{
                    Imp=0
                }

            }

        })

        var Diff=0

        diff.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { radioGroup, i ->
            when(i){
                R.id.addhomework_easy->{
                    Diff=1
                }
                R.id.addhomework_inter->{
                    Diff=2
                }
                R.id.addhomework_hard->{
                    Diff=3
                }else->{
                Diff=0
            }

            }

        })
        db= AppDatabase.getInstance(c)

        close.setOnClickListener(View.OnClickListener {
            this.dismiss()
        })
        save.setOnClickListener(View.OnClickListener {
            val Title=title.text.toString()
            val Desc=desc.text.toString()
            val randomNumber = (1..10000).random()
            db.homeworkDao().insert(Task(randomNumber,Title,Desc,Diff,Imp,Class))
            onSaveClicked.saveClicked()
            this.dismiss()

        })







        return view

    }





}


