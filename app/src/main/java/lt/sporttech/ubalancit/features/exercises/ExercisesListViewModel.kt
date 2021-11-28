package lt.sporttech.ubalancit.features.exercises

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import lt.sporttech.ubalancit.di.AppModule
import lt.sporttech.ubalancit.di.DaggerAppComponent
import lt.sporttech.ubalancit.features.workout.WorkoutRepository
import javax.inject.Inject

class ExercisesListViewModel: ViewModel() {

    @Inject lateinit var repository: WorkoutRepository
    val state = mutableStateOf<ExercisesListState>(ExercisesListState.Loading)

    fun provideDependencies(context: Context) {
        DaggerAppComponent
            .builder()
            .appModule(AppModule(context.applicationContext))
            .build()
            .inject(this)
    }

    fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            state.value = ExercisesListState.Loading

            state.value = when (repository.shouldWorkOutToday()) {
                true -> ExercisesListState.WorkoutDay(repository.loadData())
                false -> ExercisesListState.RelaxDay
            }
        }
    }
}