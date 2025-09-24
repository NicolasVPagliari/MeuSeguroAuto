package com.github.nicolasvpagliari.meuseguroauto.components

import QuoteInput
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.PersonOutline
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.github.nicolasvpagliari.meuseguroauto.R
import com.github.nicolasvpagliari.meuseguroauto.model.CarInsurancePlan
import com.github.nicolasvpagliari.meuseguroauto.model.VehiclesTypes

@Composable
fun QuoteForm(
    state: QuoteInput,
    onChange: (QuoteInput) -> Unit,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement =
            Arrangement.spacedBy(16.dp)
    ) {
        CustomLabel(stringResource(R.string.label_customer_data))

        var name by remember { mutableStateOf("") }

        CustomTextInput(
            value = state.name,
            onValueChange = { onChange(state.copy(name = it))
            },
            leadingIcon = Icons.Default.PersonOutline,
            label = stringResource(R.string.label_name)
        )

        CustomTextInput(
            value = state.email,
            onValueChange = { onChange(state.copy(email = it))
            },
            leadingIcon = Icons.Default.Email,
            label = stringResource(R.string.label_email),
            keyboardType = KeyboardType.Email
        )

        CustomTextInput(
            value = state.phone,
            onValueChange = { onChange(state.copy(phone = it))
            },
            leadingIcon = Icons.Default.Phone,
            label = stringResource(R.string.label_phone),
            keyboardType = KeyboardType.Phone
        )

        CustomDropdown(
            label = "Plano",
            options = CarInsurancePlan.entries,
            selected = state.carInsurancePlan,
            onSelect = {onChange(state.copy(carInsurancePlan = it))},
            optionLabel = {it.label}
        )

        CustomDropdown(
            label = "Veículo",
            options = VehiclesTypes.entries,
            selected = state.vehiclesTypes,
            onSelect = {onChange(state.copy(vehiclesTypes = it))},
            optionLabel = {it.label}
        )

        CustomSlider(
            label = stringResource(R.string.label_age),
            value = state.age,
            valueRange = 18..80,
            onValueChange = { onChange(state.copy(age = it)) }
        )

        CustomSlider(
            label = stringResource(R.string.label_discount),
            value = state.discount,
            valueRange = 0..10,
            onValueChange = { onChange(state.copy(discount = it))}
        )

        CustomSwitch(
            label = stringResource(R.string.label_has_garage),
            checked = state.hasGarage,
            onCheckedChange = {
                onChange(
                    state.copy(hasGarage = it)
                )
            }
        )

        CustomSwitch(
            label = stringResource(R.string.label_comercial_use),
            checked = state.isCommercialUseVehicle,
            onCheckedChange = {
                onChange(
                    state.copy(isCommercialUseVehicle = it)
                )
            }
        )

        CustomCheckbox(
            label = stringResource(R.string.label_life_insurance),
            checked = state.wantsLifeInsurance,
            onCheckedChange = {
                onChange (
                    state.copy(wantsLifeInsurance = it)
                )
            }
        )

        CustomCheckbox(
            label =
                stringResource(R.string.label_accepts_marketing_communication),
            checked = state.acceptsMarketingCommunication,
            onCheckedChange = {
                onChange(
                    state.copy(acceptsMarketingCommunication =
                        it)
                )
            }
        )
    }

}