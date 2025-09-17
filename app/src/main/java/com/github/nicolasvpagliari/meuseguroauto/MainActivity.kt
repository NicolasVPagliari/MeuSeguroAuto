package com.github.nicolasvpagliari.meuseguroauto

import CustomDropdown
import QuoteInput
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.PersonOutline
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.nicolasvpagliari.meuseguroauto.Components.CustomLabel
import com.github.nicolasvpagliari.meuseguroauto.Components.CustomSlider
import com.github.nicolasvpagliari.meuseguroauto.Components.CustomTextInput
import com.github.nicolasvpagliari.meuseguroauto.model.CarInsurancePlan
import com.github.nicolasvpagliari.meuseguroauto.model.VehiclesTypes
import com.github.nicolasvpagliari.meuseguroauto.ui.theme.MeuSeguroAutoTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MeuSeguroAutoTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(title = {
                            Row(
                                verticalAlignment =
                                    Alignment.CenterVertically
                            ) {
                                Image(
                                    painter = painterResource(
                                        id =
                                            R.drawable.ic_top_app_bar
                                    ),
                                    contentDescription = "Logo",
                                    modifier = Modifier
                                        .size(32.dp)
                                        .clip(CircleShape),
                                    colorFilter =
                                        ColorFilter.tint(MaterialTheme.colorScheme.primary)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    getString(R.string.app_name), color
                                    = MaterialTheme.colorScheme.primary
                                )
                            }
                        })
                    },
                    modifier = Modifier.fillMaxSize(),
                ) { innerPadding ->
                    QuoteScreen(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

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

    }


}

@Composable
fun QuoteScreen(modifier: Modifier = Modifier) {
    var input by rememberSaveable(
        stateSaver = Saver(
            save = {
                listOf(
                    it.name,
                    it.email,
                    it.phone,
                    it.carInsurancePlan,
                    it.vehiclesTypes
                )
            },
            restore = {
                QuoteInput(
                    name = it[0] as String,
                    email = it[1] as String,
                    phone = it[2] as String,
                    carInsurancePlan = CarInsurancePlan.entries[it[3] as Int],
                    vehiclesTypes = VehiclesTypes.entries[it[4] as Int]
                )
            })
    ) { mutableStateOf(QuoteInput()) }
    QuoteForm(
        state = input,
        onChange = { input = it },
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun QuoteScreenPreview() {
    MeuSeguroAutoTheme { QuoteScreen() }
}