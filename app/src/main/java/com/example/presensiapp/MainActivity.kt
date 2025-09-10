package com.example.presensiapp

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.presensiapp.databinding.ActivityMainBinding
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up spinner for attendance status
        val attendanceStatus = arrayOf("Hadir Tepat Waktu", "Sakit", "Terlambat", "Izin")
        val adapterStatus = ArrayAdapter(this, android.R.layout.simple_spinner_item, attendanceStatus)
        adapterStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerStatus.adapter = adapterStatus

        // Set up button listener
        binding.btnSubmit.setOnClickListener {
            val selectedDate = Calendar.getInstance().apply {
                timeInMillis = binding.calendarView.date
            }
            val month = selectedDate.get(Calendar.MONTH) + 1
            val day = selectedDate.get(Calendar.DAY_OF_MONTH)
            val year = selectedDate.get(Calendar.YEAR)
            val hour = binding.timePicker.hour
            val minute = binding.timePicker.minute

            val status = binding.spinnerStatus.selectedItem.toString()
            val keterangan = binding.etKeterangan.text.toString()

            val message = "Presensi berhasil pada $day/$month/$year, pukul $hour:$minute. Status: $status, Keterangan: $keterangan"
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        }
    }
}