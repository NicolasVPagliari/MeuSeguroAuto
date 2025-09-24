package com.github.nicolasvpagliari.meuseguroauto.ui.screens.quote

import QuoteInput
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.github.nicolasvpagliari.meuseguroauto.QuoteForm
import com.github.nicolasvpagliari.meuseguroauto.model.CarInsurancePlan
import com.github.nicolasvpagliari.meuseguroauto.model.VehiclesTypes

@Composable
fun QuoteScreen(modifier: Modifier = Modifier) {
    var input by rememberSaveable(
        stateSaver = Saver(
            save = {
                listOf(
                    it.name,
                    it.email,
                    it.phone,
                    it.vehiclePrice,
                    it.carInsurancePlan.ordinal,
                    it.vehiclesTypes.ordinal,
                    it.age,
                    it.discount,
                    it.hasGarage,
                    it.isCommercialUseVehicle,
                    it.wantsLifeInsurance,
                    it.acceptsMarketingCommunication
                )
            },
            restore = {
                QuoteInput(
                    name = it[0] as String,
                    email = it[1] as String,
                    phone = it[2] as String,
                    vehiclePrice = it[3] as String,
                    carInsurancePlan = CarInsurancePlan.entries[it[4] as Int],
                    vehiclesTypes = VehiclesTypes.entries[it[5] as Int],
                    age = it[6] as Int,
                    discount = it[7] as Int,
                    hasGarage = it[8] as Boolean,
                    isCommercialUseVehicle = it[9] as Boolean,
                    wantsLifeInsurance = it[10] as Boolean,
                    acceptsMarketingCommunication = it[11] as Boolean,
                )
            })
    ) { mutableStateOf(QuoteInput()) }
    QuoteForm(
        state = input,
        onChange = { input = it },
        modifier = modifier
    )

    val price = rememberSaveable(input) {
        calculateQuote(input)
    }
}

fun calculateQuote(input: QuoteInput): Double {
    // Preço base = 4% do valor do veículo
    val vehiclePrice =
        if (input.vehiclePrice.isEmpty()) 0.0 else
            (input.vehiclePrice.toDouble()) / 100
    // O preco base será 4% do valor do veiculo
    val basePrice = vehiclePrice * 0.04
    // Aplica o fator do plano
    var total = basePrice * input.carInsurancePlan.factor
    // Ajuste pelo tipo de veículo
    total *= input.vehiclesTypes.factor
    // Ajuste por idade do motorista
    total += when {
        input.age < 25 -> 200.0
        input.age in 25..65 -> 0.0
        else -> 100.0
    }
    // Ajuste pelo bônus (desconto)
    // cada ponto de bônus reduz 10 reais
    total -= input.discount * 10

    // Garagem
    if (!input.hasGarage) {
        total += 150.0
    }
    // Uso comercial
    if (input.isCommercialUseVehicle) {
        total += 300.0
    }
    // Seguro de vida
    if (input.wantsLifeInsurance) {
        total += 500.0
    }
    // Limite mínimo de valor
    if (total < 0) total = 0.0
    return total
}
