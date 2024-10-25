package com.example.medicinesideeffects

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
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
    private lateinit var medicines: List<Medicine>
    private lateinit var adapter: MedicineAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etSearch = findViewById(R.id.etSearch)
        btnSearch = findViewById(R.id.btnSearch)
        lvMedicineList = findViewById(R.id.lvMedicineList)

        Log.d("MainActivity", "onCreate called")

        // Загрузка данных из JSON
        medicines = DataGenerator.getMedicines(this)

        // Инициализация адаптера с пустым списком
        adapter = MedicineAdapter(this, emptyList())
        lvMedicineList.adapter = adapter

        btnSearch.setOnClickListener {
            val query = etSearch.text.toString()
            Log.d("MainActivity", "Search query: $query")
            val filteredMedicines = medicines.filter { it.name.contains(query, ignoreCase = true) }
            Log.d("MainActivity", "Filtered medicines: ${filteredMedicines.size}")

            // Обновление адаптера с новыми отфильтрованными данными
            adapter.updateData(filteredMedicines)
        }

        lvMedicineList.setOnItemClickListener { _, _, position, _ ->
            val selectedMedicine = adapter.getItem(position) as Medicine
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