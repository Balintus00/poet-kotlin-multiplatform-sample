package hu.bme.aut.ixnoyb.poetkotlinmultiplatformsample.compose

import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

// https://github.com/JetBrains/compose-jb/issues/2284

@Composable
actual fun TextFieldFix(
    enabled: Boolean,
    modifier: Modifier,
    onValueChange: (String) -> Unit,
    singleLine: Boolean,
    value: String,
) {
    TextField(
        enabled = enabled,
        modifier = modifier,
        onValueChange = onValueChange,
        singleLine = singleLine,
        value = value,
    )
}
