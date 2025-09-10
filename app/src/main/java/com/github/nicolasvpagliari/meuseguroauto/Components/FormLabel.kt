package com.github.nicolasvpagliari.meuseguroauto.Components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomLabel(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        style =
            MaterialTheme.typography.labelLarge.copy(
                color =
                    MaterialTheme.colorScheme.onSurface
            ),
        fontSize = 18.sp,
        modifier = modifier.padding(bottom = 4.dp)
    )
}