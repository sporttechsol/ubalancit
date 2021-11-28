package lt.sporttech.ubalancit.features.chooseplan

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class ChoosePlanViewModel: ViewModel() {

    val state = mutableStateOf(ChoosePlanState(false, false, false, false))

    fun onGymClick() {
        val oldState = state.value
        state.value = oldState.copy(
            isGymSelected = !oldState.isGymSelected,
            isHomeSelected = if (oldState.isGymSelected) oldState.isHomeSelected else false
        )
    }

    fun onHomeClick() {
        val oldState = state.value
        state.value = oldState.copy(
            isHomeSelected = !oldState.isHomeSelected,
            isGymSelected = if (oldState.isHomeSelected) oldState.isGymSelected else false
        )
    }

    fun onFullWorkoutClick() {
        val oldState = state.value
        state.value = oldState.copy(
            isFullWorkoutSelected = !oldState.isFullWorkoutSelected,
            is15minSelected = if (oldState.isFullWorkoutSelected) oldState.is15minSelected else false
        )
    }

    fun onFifteenMinutesClick() {
        val oldState = state.value
        state.value = oldState.copy(
            is15minSelected = !oldState.is15minSelected,
            isFullWorkoutSelected = if (oldState.is15minSelected) oldState.isFullWorkoutSelected else false
        )
    }

    fun onSubmit() {
        // TODO...
    }
}