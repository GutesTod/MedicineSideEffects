package com.example.medicinesideeffects

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var etSearch: EditText
    private lateinit var btnSearch: Button
    private lateinit var lvMedicineList: ListView

    private val medicines = listOf(
        Medicine("Аспирин", listOf(
            SideEffect("Тошнота", "Ощущение тошноты", "Часто"),
            SideEffect("Головная боль", "Болезненное ощущение в голове", "Редко")
        )),
        Medicine("Парацетамол", listOf(
            SideEffect("Аллергическая реакция", "Крапивница, отек", "Редко"),
            SideEffect("Потеря аппетита", "Снижение желания есть", "Редко")
        ))
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etSearch = findViewById(R.id.etSearch)
        btnSearch = findViewById(R.id.btnSearch)
        lvMedicineList = findViewById(R.id.lvMedicineList)

        btnSearch.setOnClickListener {
            val query = etSearch.text.toString()
            val filteredMedicines = medicines.filter { it.name.contains(query, ignoreCase = true) }
            lvMedicineList.adapter = MedicineAdapter(this, filteredMedicines)
        }

        lvMedicineList.setOnItemClickListener { _, _, position, _ ->
            val selectedMedicine = lvMedicineList.adapter.getItem(position) as Medicine
            showSideEffectsDialog(selectedMedicine)
        }
    }

    private fun showSideEffectsDialog(medicine: Medicine) {
        val sideEffectsText = medicine.sideEffects.joinToString("\n\n") {
            "${it.name}\n${it.description}\nЧастота: ${it.frequency}"
        }

        AlertDialog.Builder(this)
            .setTitle("Побочные эффекты ${medicine.name}")
            .setMessage(sideEffectsText)
            .setPositiveButton("OK", null)
            .show()
    }
}