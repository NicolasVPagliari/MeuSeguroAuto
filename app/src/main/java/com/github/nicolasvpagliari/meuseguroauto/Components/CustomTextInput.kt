package com.github.nicolasvpagliari.meuseguroauto.Components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun CustomTextInput(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    singleLine: Boolean = true,
    isPassword: Boolean = false,
    leadingIcon: ImageVector? = null,
) {
    var passwordVisible by remember {
        mutableStateOf(false) }
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        singleLine = singleLine,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        // Configurações do teclado
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            // ação padrão (avançar para próximo campo)
            imeAction = ImeAction.Next
        ),
        leadingIcon = leadingIcon?.let {
            { Icon(imageVector = it, contentDescription = null) }
        },
        // Se for senha, adiciona ícone para mostrar/ocultar
        visualTransformation = if (isPassword && !passwordVisible)
            PasswordVisualTransformation() else VisualTransformation.None,
        trailingIcon = {
            if (isPassword) {
                val image = if (passwordVisible) Icons.Default.Visibility
                else Icons.Default.VisibilityOff
                IconButton(onClick = { passwordVisible = !passwordVisible })
                {
                    Icon(imageVector = image, contentDescription = "Alternar visibilidade")
                }
            }
        }
    )
}