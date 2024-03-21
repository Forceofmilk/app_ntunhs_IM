package com.example.loginpage

import android.app.AlertDialog
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.DatePicker
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import java.util.Calendar

private fun setDateFormat(Year:Int, month:Int, day:Int):String{
    return "$Year-${month+1}-$day"
}

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        畫面組件設定
        val account_ID = findViewById<EditText>(R.id.editText_ACCID)
        val password = findViewById<EditText>(R.id.editTextText_PWD)
        val userName = findViewById<EditText>(R.id.editTextText_Name)
        val birthday = findViewById<EditText>(R.id.editTextDate_BTHDAY)
        val radGrp_gender = findViewById<RadioGroup>(R.id.radGrpGender)
        val gender_Male = findViewById<RadioButton>(R.id.radioBtn_Male)
        val gender_Female = findViewById<RadioButton>(R.id.radioBtn_Female)
        val vehicle_car = findViewById<CheckBox>(R.id.checkBox_car)
        val vehicle_bike = findViewById<CheckBox>(R.id.checkBox_bike)
        val vehicle_mortoCycle = findViewById<CheckBox>(R.id.checkBox_Mortor)
        val send_btn = findViewById<Button>(R.id.send_button)

        send_btn.setOnClickListener{

    //    Account ID get
            var ID = ""
            ID = account_ID.text.toString()

    //    Password get
            var PWD = ""
            PWD = password.text.toString()
            val showPassword = password.text.toString()
            var resultPasswordView = ""
            if (showPassword.isNotEmpty()) {
                // Create hidden middle string with '*'
                val hiddenMiddle = "*".repeat(password.length() - 2)

                // Extract first and last characters
                val firstChar = showPassword.first()
                val lastChar = showPassword.last()
                resultPasswordView  = "$firstChar$hiddenMiddle$lastChar"
            }

    //    User name get
            var user_name = ""
            user_name = userName.text.toString()

    //    Birthday get
            birthday.setOnClickListener {
                val calendar = Calendar.getInstance()
                val year = calendar.get(Calendar.YEAR)
                val month = calendar.get(Calendar.MONTH)
                val day = calendar.get(Calendar.DAY_OF_MONTH)

                DatePickerDialog(this, {_, year, month, day ->
                    run{
                        var format = "${setDateFormat(year, month, day)}"
                        birthday.setText(format)
                    }
                },year,month,day).show()
            }

            var BIRTHDAY = birthday.text.toString()

    //    Gender get
            var genderSelect = ""
            radGrp_gender.setOnCheckedChangeListener { _, checkedID ->
                genderSelect = radGrp_gender.findViewById<RadioButton>(checkedID).text.toString()
            }

    //    Vehicle get
            var vehicleSelect = ""
            if(vehicle_bike.isChecked()){
                vehicleSelect += vehicle_bike.text.toString()
            }
            if(vehicle_car.isChecked()){
                vehicleSelect += vehicle_car.text.toString()
            }
            if(vehicle_mortoCycle.isChecked()){
                vehicleSelect += vehicle_mortoCycle.text.toString()
            }

            var all_select: String
            all_select = ID + "\n" + resultPasswordView + "\n" + user_name + "\n" +BIRTHDAY + "\n" + genderSelect + "\n" + vehicleSelect

            AlertDialog.Builder(this).setMessage(all_select).setTitle("Your Information").show()
        }
    }

}
