package com.github.nicolasvpagliari.meuseguroauto.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.github.nicolasvpagliari.meuseguroauto.R

@Composable
fun QuoteSummary(price: Double, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            stringResource(R.string.label_quote_summary),
            style = MaterialTheme.typography.titleMedium
        )
        HorizontalDivider()
        Text(
            text =
                stringResource(R.string.label_estimated_monthly),
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = stringResource(R.string.price_with_label,
                price),
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )
        Text(
            text =
                stringResource(R.string.label_reference_values),
            style = MaterialTheme.typography.bodySmall
        )
    }
}