package com.farhanryanda.kalkulatorbmi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var editUmur: EditText
    lateinit var editTinggi: EditText
    lateinit var editBerat: EditText
    lateinit var btnCalculate: Button
    lateinit var btnReset: Button
    lateinit var resultUmur: TextView
    lateinit var resultTinggi: TextView
    lateinit var resultBerat: TextView
    lateinit var resultBmi: TextView
    lateinit var resultKategori: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editUmur = findViewById(R.id.edt_umur)
        editTinggi = findViewById(R.id.edt_tinggi)
        editBerat = findViewById(R.id.edt_berat)
        btnCalculate = findViewById(R.id.btn_calculate)
        btnReset = findViewById(R.id.btn_reset)
        resultUmur = findViewById(R.id.tv_result_umur)
        resultTinggi = findViewById(R.id.tv_result_tinggi)
        resultBerat = findViewById(R.id.tv_result_berat)
        resultBmi = findViewById(R.id.tv_result_bmi)
        resultKategori = findViewById(R.id.tv_result_kategori)

        btnCalculate.setOnClickListener {
            editBerat.onEditorAction(EditorInfo.IME_ACTION_DONE)
            editUmur.onEditorAction(EditorInfo.IME_ACTION_DONE)
            editTinggi.onEditorAction(EditorInfo.IME_ACTION_DONE)
            var inputUmur = editUmur.text.toString()
            var inputTinggi = editTinggi.text.toString()
            var inputBerat = editBerat.text.toString()
            var isEmptyFields = false
            if (inputUmur.isEmpty()) {
                isEmptyFields = true
                editUmur.error = "Umur Tidak Boleh Kosong"
            }
            if (inputTinggi.isEmpty()) {
                isEmptyFields = true
                editTinggi.error = "Tinggi Tidak Boleh Kosong"
            }
            if (inputBerat.isEmpty()) {
                isEmptyFields = true
                editBerat.error = "Berat Tidak Boleh Kosong"
            }
            if (!isEmptyFields) {
                resultUmur.text = "Umur Anda\t: $inputUmur tahun"
                resultTinggi.text = "Tinggi\t: $inputTinggi cm"
                resultBerat.text = "Berat Badan\t: $inputBerat kg"

                var bmi = inputBerat.toDouble() / ((inputTinggi.toDouble()/100) * (inputTinggi.toDouble()/100))
                resultBmi.text = "BMI anda\t: %.2f".format(bmi)
                var kategori = ""
                if (bmi < 16) {
                    kategori = "Terlalu Kurus"
                } else if (bmi in 16.0..17.0) {
                    kategori = "Cukup Kurus"
                } else if (bmi in 17.0..18.5) {
                    kategori = "Sedikit Kurus"
                } else if (bmi in 18.5..25.0) {
                    kategori = "Normal"
                } else if (bmi in 25.0..30.0) {
                    kategori = "Gemuk"
                } else if (bmi in 30.0..35.0) {
                    kategori = "Obesitas Kelas 1"
                } else if (bmi in 35.0..40.0) {
                    kategori = "Obesitas Kelas 2"
                } else if (bmi > 40) {
                    kategori = "Obesitas Kelas 3"
                }
                resultKategori.text = "Kategori\t: $kategori"
            }
        }

        btnReset.setOnClickListener {
            resetData()
        }
    }

    fun resetData() {
        editBerat.setText("")
        editUmur.setText("")
        editTinggi.setText("")
    }
}