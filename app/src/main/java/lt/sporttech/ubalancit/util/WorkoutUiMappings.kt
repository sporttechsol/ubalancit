package lt.sporttech.ubalancit.util

import androidx.compose.ui.graphics.Color
import lt.sporttech.ubalancit.core.alias.Milliseconds

internal fun setsRelationColor(isEnabled: Boolean): Color =
    if (isEnabled) IOS_NATIVE_BLUE else Color.Gray

internal fun durationString(
    repetitions: Int?,
    time: Milliseconds?
): String = repetitions
    ?.let { "x $it" }
    ?: time?.let {
        if (it >= 60_000L) "${it / 60_000L} min"
        else "${it / 1000L} s"
    }
    ?: ""