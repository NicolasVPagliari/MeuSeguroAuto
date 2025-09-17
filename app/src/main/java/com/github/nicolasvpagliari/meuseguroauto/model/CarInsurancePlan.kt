package com.github.nicolasvpagliari.meuseguroauto.model

enum class CarInsurancePlan (
    val label: String,
    val factor: Double
) {
    ESSENCIAL("Essencial", 1.0),

    STANDART("Padrão", factor = 1.2),

    FULL("Completo", factor = 1.5)
}