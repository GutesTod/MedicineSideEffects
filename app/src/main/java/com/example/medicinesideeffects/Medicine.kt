package com.example.medicinesideeffects

data class Medicine(
    val name: String,
    val sideEffects: List<SideEffect>
)

data class SideEffect(
    val name: String,
    val description: String,
    val frequency: String
)
