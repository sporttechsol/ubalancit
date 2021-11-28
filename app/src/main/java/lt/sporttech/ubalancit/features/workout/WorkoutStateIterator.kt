package lt.sporttech.ubalancit.features.workout

import lt.sporttech.ubalancit.core.model.SetResult
import lt.sporttech.ubalancit.core.model.ScheduledWorkoutDay

class WorkoutStateIterator(
    private val day: ScheduledWorkoutDay,
) {

    private var nextState: WorkoutState.Progressing? = WorkoutState.Progressing(
        day = day,
        complexIndex = 0,
        exerciseIndex = 0,
        setIndex = 0,
        setResults = listOf(),
    )

    fun hasNext(): Boolean {
        return nextState != null
    }

    fun initial(): WorkoutState.Progressing {
        if (nextState?.complexIndex != 0 || nextState?.exerciseIndex != 0 || nextState?.setIndex != 0) {
            throw IllegalStateException("This iterator is not in initial state")
        }

        return initialOrNext(null)
    }

    fun next(resultOfThisSet: SetResult): WorkoutState.Progressing {
        return initialOrNext(resultOfThisSet)
    }

    private fun initialOrNext(resultOfThisSet: SetResult?): WorkoutState.Progressing {
        var currentState = nextState
            ?: throw IllegalStateException("No next state")

        if (resultOfThisSet != null) {
            currentState =  currentState.copy(
                setResults = currentState.setResults + resultOfThisSet
            )
        }

        val currentComplex = currentState.day.complexes[currentState.complexIndex]
        val currentExercise = currentComplex.exercises[currentState.exerciseIndex]

        nextState = if (currentState.setIndex + 1 < currentExercise.sets.size) {
            // Just switch to next set.
            currentState.copy(
                setIndex = currentState.setIndex + 1,
            )
        } else if (currentState.exerciseIndex + 1 < currentComplex.exercises.size) {
            // Switch to next exercise.
            currentState.copy(
                exerciseIndex = currentState.exerciseIndex + 1,
                setIndex = 0,
            )
        } else if (currentState.complexIndex + 1 < currentState.day.complexes.size) {
            // Switch to next complex.
            currentState.copy(
                complexIndex = currentState.complexIndex + 1,
                exerciseIndex = 0,
                setIndex = 0,
            )
        } else {
            null
        }

        return currentState.also {
            android.util.Log.v("CharlieDebug", "Results: ${it.setResults.joinToString { it.name }}")
        }
    }
}