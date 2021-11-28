package lt.sporttech.ubalancit.features.workout

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import lt.sporttech.ubalancit.core.model.DayOfWeek
import lt.sporttech.ubalancit.core.model.SetResult
import lt.sporttech.ubalancit.di.AppModule
import lt.sporttech.ubalancit.di.DaggerAppComponent
import javax.inject.Inject

class WorkoutViewModel: ViewModel() {

    @Inject lateinit var repository: WorkoutRepository
    private var iterator: WorkoutStateIterator? = null
    val state = mutableStateOf<WorkoutState>(WorkoutState.Loading)

    fun provideDependencies(appContext: Context) {
        DaggerAppComponent
            .builder()
            .appModule(AppModule(appContext))
            .build()
            .inject(this)
    }

    fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            val data = repository.getData(DayOfWeek.MONDAY)
            iterator = WorkoutStateIterator(data).also { iterator ->
                state.value = iterator.initial()
            }
        }
    }

    fun submitResult(result: SetResult, showResultPercentage: (Int) -> Unit) {
        val iterator = iterator ?: return
        if (iterator.hasNext()) {
            state.value = iterator.next(result)
        } else {
            val lastState = (state.value as WorkoutState.Progressing)
            val resultPercentage = lastState
                .copy(setResults = lastState.setResults + result)
                .computePercentage()
            showResultPercentage(resultPercentage)
        }
    }
}