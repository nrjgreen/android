package nl.nrjhub.companion.android.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.wear.compose.material3.Checkbox
import androidx.wear.compose.material3.Switch
import nl.nrjhub.companion.android.common.R
import nl.nrjhub.companion.android.theme.getCheckboxColors
import nl.nrjhub.companion.android.theme.getSwitchColors

@Composable
fun ToggleSwitch(isChecked: Boolean) {
    val description = stringResource(if (isChecked)R.string.enabled else R.string.disabled)
    Switch(
        checked = isChecked,
        modifier = Modifier.semantics {
            this.contentDescription = description
        },
        colors = getSwitchColors()
    )
}

@Composable
fun ToggleCheckbox(isChecked: Boolean) {
    val description = stringResource(if (isChecked) R.string.show else R.string.hide)
    Checkbox(
        checked = isChecked,
        modifier = Modifier.semantics {
            this.contentDescription = description
        },
        colors = getCheckboxColors()
    )
}
