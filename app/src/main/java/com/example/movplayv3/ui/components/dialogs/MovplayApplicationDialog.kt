package com.example.movplayv3.ui.components.dialogs

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.movplayv3.R

@Composable
fun MovplayApplicationDialog(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit = {},
    infoText: String? = null,
    confirmButton: @Composable (() -> Unit)? = null,
    dismissButton: @Composable (() -> Unit)? = null
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = onDismissRequest,
        title = {
            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(R.drawable.ic_movplay),
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )
        },
        text = {
            infoText?.let { text -> Text(text = text) }
        },
        confirmButton = {
            confirmButton?.invoke()
        },
        dismissButton = {
            dismissButton?.invoke()
        }
    )
}