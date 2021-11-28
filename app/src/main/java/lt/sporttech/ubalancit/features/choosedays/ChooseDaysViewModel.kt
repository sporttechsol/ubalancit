package lt.sporttech.ubalancit.features.choosedays

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import lt.sporttech.ubalancit.core.model.DayOfWeek
import lt.sporttech.ubalancit.di.AppModule
import lt.sporttech.ubalancit.di.DaggerAppComponent
import lt.sporttech.ubalancit.features.workout.WorkoutRepository
import javax.inject.Inject

class ChooseDaysViewModel: ViewModel() {

    @Inject lateinit var workoutRepository: WorkoutRepository
    val state = mutableStateOf(ChooseDaysState(emptyList()))

    fun provideDependencies(context: Context) {
        DaggerAppComponent
            .builder()
            .appModule(AppModule(context.applicationContext))
            .build()
            .inject(this)
    }

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

    fun submit(onComplete: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            workoutRepository.saveWorkoutDays(state.value.selectedDays)
            withContext(Dispatchers.Main) {
                onComplete()
            }
        }
    }
}