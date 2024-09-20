import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStreamReader
import android.util.Log
import com.example.medicinesideeffects.Medicine

object DataGenerator {

    fun getMedicines(context: Context): List<Medicine> {
        return readMedicinesFromJson(context)
    }

    private fun readMedicinesFromJson(context: Context): List<Medicine> {
        Log.d("DataGenerator", "Reading medicines from JSON")
        val inputStream = context.assets.open("medicines.json")
        val reader = InputStreamReader(inputStream)
        val type = object : TypeToken<List<Medicine>>() {}.type
        val medicines = Gson().fromJson<List<Medicine>>(reader, type)
        Log.d("DataGenerator", "Medicines loaded: ${medicines.size}")
        return medicines
    }
}