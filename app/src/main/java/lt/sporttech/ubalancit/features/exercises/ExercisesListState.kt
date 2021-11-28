package lt.sporttech.ubalancit.features.exercises

import lt.sporttech.ubalancit.core.model.ScheduledWorkoutDay

sealed class ExercisesListState {
    object Loading: ExercisesListState()
    object RelaxDay: ExercisesListState()

    data class WorkoutDay(
        val data: ScheduledWorkoutDay,
    ): ExercisesListState()
}