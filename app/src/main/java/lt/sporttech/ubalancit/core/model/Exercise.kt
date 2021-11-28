package lt.sporttech.ubalancit.core.model

import androidx.annotation.DrawableRes

data class Exercise(
    val id: Int,
    val title: String,
    @DrawableRes val imageRes: Int,
    val sets: List<Set>,
)