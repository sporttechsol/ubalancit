package lt.sporttech.ubalancit.core.model

data class Complex(
    val id: Int,
    val title: String,
    val exercises: List<Exercise>,
)