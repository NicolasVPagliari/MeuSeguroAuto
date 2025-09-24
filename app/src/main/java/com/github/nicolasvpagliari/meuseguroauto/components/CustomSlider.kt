package com.github.nicolasvpagliari.meuseguroauto.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun CustomSlider(
    label: String,
    value: Int,
    valueRange: IntRange,
    onValueChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(Modifier.fillMaxWidth(), horizontalArrangement =
        Arrangement.SpaceBetween) {
        Text(label)
        Text(value.toString(), fontWeight =
            FontWeight.SemiBold)
    }

    Slider(
        // Slider trabalha com Float, por isso converte
        value = value.toFloat(),
        // converte Float -> Int
        onValueChange = { onValueChange(it.toInt()) },
        valueRange =
            valueRange.first.toFloat()..valueRange.last.toFloat(),
        colors = SliderDefaults.colors(
            // Cor do círculo
            thumbColor = MaterialTheme.colorScheme.primary,
            // Cor da trilha já percorrida
            activeTrackColor =
                MaterialTheme.colorScheme.primary,
            // Cor da trilha restante
            inactiveTrackColor = Color(0x611565C0),
        ),
        modifier = Modifier.padding(horizontal = 8.dp)
    )
}