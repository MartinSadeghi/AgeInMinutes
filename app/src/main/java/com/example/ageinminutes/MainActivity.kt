package com.example.ageinminutes

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        datePickerBtn.setOnClickListener { view ->
            clickDatePicker(view)

        }
    }

    private fun clickDatePicker(view: View) {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog =
        DatePickerDialog(this ,

//            "Instead of _ there was view and I replaced it by system suggestion with _"
            DatePickerDialog.OnDateSetListener { _ ,selectedYear ,selectedMonth ,selectedDayOfMonth ->

//                logic of calc. into mins


                val selectedDate = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"

                tvSelectedDateTextID.text = selectedDate

                val sdf = SimpleDateFormat("dd/MM/yyyy" ,Locale.ENGLISH)

                val theDate = sdf.parse(selectedDate)
                theDate?.let {
                    val selectedDateInMins = theDate.time / 60000

                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
//                print(currentDate)

                    currentDate?.let {
                        val currentDateInMins = currentDate.time / 60000
                        val diffInMins = currentDateInMins - selectedDateInMins
                        tvSelectedDateInMinutesTextID.text = diffInMins.toString()
                    }

                }

            } ,year ,month ,day)

//        there are 3.6 million mili seconds in 1 hour * 24 and the result is 86-400-000

        datePickerDialog.datePicker.maxDate = System.currentTimeMillis() - 86_400_000
        datePickerDialog.show()
    }
}

