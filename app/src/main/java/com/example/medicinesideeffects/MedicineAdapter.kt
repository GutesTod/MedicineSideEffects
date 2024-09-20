package com.example.medicinesideeffects

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class MedicineAdapter(private val context: Context, private val medicines: List<Medicine>) : BaseAdapter() {

    override fun getCount(): Int {
        return medicines.size
    }

    override fun getItem(position: Int): Any {
        return medicines[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item_medicine, parent, false)
        val medicine = medicines[position]

        view.findViewById<TextView>(R.id.tvMedicineName).text = medicine.name

        return view
    }
}