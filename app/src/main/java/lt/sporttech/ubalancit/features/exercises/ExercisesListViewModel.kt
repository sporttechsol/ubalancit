package lt.sporttech.ubalancit.features.exercises

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import lt.sporttech.ubalancit.core.model.DayOfWeek
import lt.sporttech.ubalancit.core.model.ScheduledWorkoutDay
import lt.sporttech.ubalancit.di.AppModule
import lt.sporttech.ubalancit.di.DaggerAppComponent
import lt.sporttech.ubalancit.features.workout.WorkoutRepository
import javax.inject.Inject

class ExercisesListViewModel: ViewModel() {

    @Inject lateinit var repository: WorkoutRepository
    val dataState = mutableStateOf(ScheduledWorkoutDay(DayOfWeek.MONDAY, emptyList()))

    fun provideDependencies(context: Context) {
        DaggerAppComponent
            .builder()
            .appModule(AppModule(context.applicationContext))
            .build()
            .inject(this)
    }

    fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            dataState.value = repository.getData(DayOfWeek.MONDAY)
        }
    }
}