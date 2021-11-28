package lt.sporttech.ubalancit.features.choosedays

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import lt.sporttech.ubalancit.core.model.DayOfWeek

class ChooseDaysViewModel: ViewModel() {

    val state = mutableStateOf(ChooseDaysState(emptyList()))

    fun toggleSelection(day: DayOfWeek) {
        val selectedDays = state.value.selectedDays

        state.value = state.value.copy(
            selectedDays = if (day in selectedDays) {
                selectedDays.filter { it != day }
            } else {
                selectedDays + day
            }
        )
    }

    fun onSubmit() {
        // ...
    }
}