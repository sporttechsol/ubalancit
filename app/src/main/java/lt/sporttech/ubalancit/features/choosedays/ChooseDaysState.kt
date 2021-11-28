package lt.sporttech.ubalancit.features.choosedays

import lt.sporttech.ubalancit.core.model.DayOfWeek

data class ChooseDaysState(
    val selectedDays: List<DayOfWeek>,
)