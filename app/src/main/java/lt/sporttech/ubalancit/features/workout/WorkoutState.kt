package lt.sporttech.ubalancit.features.workout

import lt.sporttech.ubalancit.core.model.SetResult
import lt.sporttech.ubalancit.core.model.ScheduledWorkoutDay

sealed class WorkoutState {

    object Loading: WorkoutState()

    data class Progressing(
        val day: ScheduledWorkoutDay,
        val complexIndex: Int,
        val exerciseIndex: Int,
        val setIndex: Int,
        val setResults: List<SetResult>,
    ): WorkoutState() {

        fun setResultInCurrentExercise(setIndexInExercise: Int): SetResult? {
            val offset = setResults.size - setIndex
            return setResults.getOrNull(offset + setIndexInExercise)
        }

        fun computePercentage(): Int {
            val actual: Int = setResults.sumOf { result ->
                when (result) {
                    SetResult.SKIP -> 0
                    SetResult.SO_SO -> 5
                    SetResult.DONE -> 10
                } as Int
            }
            val total = 10 * setResults.size

            return (100 * actual) / total
        }
    }
}