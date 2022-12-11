package com.example.mindo_kotlin

import android.content.Context
import android.icu.text.CaseMap.Title
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.TextView
import androidx.core.view.get
import androidx.fragment.app.DialogFragment
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.MaterialTimePicker.INPUT_MODE_CLOCK
import com.google.android.material.timepicker.TimeFormat


class Add_course (var c :Context,var onsaveclicked:onSaveClicked): DialogFragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL,R.style.hm_dialog)


        }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_add_course, container, false)
        val save=view.findViewById<ImageView>(R.id.addcourse_save)
        val title=view.findViewById<TextInputEditText>(R.id.addcourse_title)
        val desc=view.findViewById<TextInputEditText>(R.id.addcourse_desc)
        val timestart=view.findViewById<TextView>(R.id.addcourse_timestart)
        val timeend=view.findViewById<TextView>(R.id.addcourse_timeend)
        val date=view.findViewById<TextView>(R.id.addcr_date)

        val alarm=view.findViewById<Switch>(R.id.addcourse_alarm)
        val close=view.findViewById<ImageView>(R.id.addcourse_close)

        var Imp=0


        val db=AppDatabase.getInstance(c)

        timestart.setOnClickListener(View.OnClickListener {
            val picker =
                MaterialTimePicker.Builder()
                    .setTimeFormat(TimeFormat.CLOCK_12H)
                    .setHour(12)
                    .setMinute(10)
                    .setTitleText("Select start time")
                    .build()

            MaterialTimePicker.Builder().setInputMode(INPUT_MODE_CLOCK)
            picker.show(parentFragmentManager, "tag");
            picker.addOnPositiveButtonClickListener {
                timestart.setText(picker.hour.toString() + ":" + picker.minute.toString()  )
            }
        })

        timeend.setOnClickListener(View.OnClickListener {
            val picker =
                MaterialTimePicker.Builder()
                    .setTimeFormat(TimeFormat.CLOCK_12H)
                    .setHour(12)
                    .setMinute(10)
                    .setTitleText("Select end time")
                    .build()

            MaterialTimePicker.Builder().setInputMode(INPUT_MODE_CLOCK)
            picker.show(parentFragmentManager, "tag");
            picker.addOnPositiveButtonClickListener {
                timeend.setText(picker.hour.toString() + ":" + picker.minute.toString()  )
            }

        })

        date.setOnClickListener {

               val builder= MaterialDatePicker.Builder.datePicker().setTitleText("Select date").setSelection(MaterialDatePicker.todayInUtcMilliseconds()).build()
                builder.show(parentFragmentManager,"tag")



        }

        save.setOnClickListener(View.OnClickListener {
            val Title=title.text.toString()
            val Desc=desc.text.toString()
            val TimeStart=timestart.text.toString()
            val TimeEnd=timeend.text.toString()
            val Alarm=alarm.isChecked
            val randomNumber = (1..10000).random()
            db.courseDao().insert(Course(randomNumber,Title,Desc,TimeStart,TimeEnd,Imp,Alarm,""))
            onsaveclicked.saveClicked()
            this.dismiss()
        })

        close.setOnClickListener(View.OnClickListener {
            this.dismiss()
        })

        return view
    }




}