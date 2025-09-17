package com.github.nicolasvpagliari.meuseguroauto.model

enum class VehiclesTypes (
    val label: String,
    val factor: Double
) {
    CAR("Carro", 1.0),

    MOTO("Moto", factor = 2.0),

    TRUCK("Caminhão", factor = 1.5)
}