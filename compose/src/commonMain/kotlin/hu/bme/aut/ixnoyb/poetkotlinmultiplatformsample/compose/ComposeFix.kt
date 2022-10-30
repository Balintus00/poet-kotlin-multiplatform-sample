package hu.bme.aut.ixnoyb.poetkotlinmultiplatformsample.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

// https://github.com/JetBrains/compose-jb/issues/2284

@Composable
expect fun TextFieldFix(
    enabled: Boolean,
    modifier: Modifier,
    onValueChange: (String) -> Unit,
    singleLine: Boolean,
    value: String,
)