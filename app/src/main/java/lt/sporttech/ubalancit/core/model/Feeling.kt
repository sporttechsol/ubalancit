package lt.sporttech.ubalancit.core.model

import androidx.annotation.DrawableRes
import lt.sporttech.ubalancit.R

enum class Feeling(@DrawableRes val iconRes: Int) {
    EASY(R.drawable.feeling_easy),
    GOOD(R.drawable.feeling_good),
    HARD(R.drawable.feeling_hard),
    TOUGH(R.drawable.feeling_tough),
}