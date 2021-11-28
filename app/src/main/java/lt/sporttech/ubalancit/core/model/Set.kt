package lt.sporttech.ubalancit.core.model

import lt.sporttech.ubalancit.core.alias.Milliseconds

data class Set(
    val id: Int,
    val repeats: Int?,
    val time: Milliseconds?,
)