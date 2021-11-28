package lt.sporttech.ubalancit.data.parser

import android.content.Context
import android.util.JsonReader
import lt.sporttech.ubalancit.R
import lt.sporttech.ubalancit.core.alias.Milliseconds
import lt.sporttech.ubalancit.core.model.Complex
import lt.sporttech.ubalancit.core.model.Exercise
import lt.sporttech.ubalancit.core.model.Set
import lt.sporttech.ubalancit.core.model.WorkoutDay
import lt.sporttech.ubalancit.core.model.WorkoutWeek
import lt.sporttech.ubalancit.util.imageResourceFromString
import javax.inject.Inject

class WorkoutWeekParser @Inject constructor(
    private val appContext: Context,
) {

    fun parse(
        reader: JsonReader,
        weekId: Int
    ): WorkoutWeek {
        val days = mutableListOf<WorkoutDay>()
        reader.beginObject()

        var dayId = 7 * weekId
        while (reader.hasNext()) {
            reader.nextName()
            days.add(parseDay(reader, dayId++))
        }

        reader.endObject()
        return WorkoutWeek(weekId, days)
    }

    private fun parseDay(
        reader: JsonReader,
        dayId: Int
    ): WorkoutDay {
        val complexes = mutableListOf<Complex>()
        reader.beginObject()

        var complexId = 8 * dayId
        while (reader.hasNext()) {
            val fieldTitle = reader.nextName()
            val (complexTitle, exercises) = parseComplex(reader, complexId)

            complexes.add(
               Complex(
                   complexId++,
                   complexTitle ?: fieldTitle,
                   exercises
               )
            )
        }

        reader.endObject()
        return WorkoutDay(dayId, complexes)
    }

    private fun parseComplex(
        reader: JsonReader,
        complexId: Int
    ): Pair<String?, List<Exercise>> {
        var title: String? = null
        var exercises = listOf<Exercise>()
        reader.beginObject()

        while (reader.hasNext()) {
            when (val fieldName = reader.nextName()) {
                "name" -> title = reader.nextString()
                "exercises" -> exercises = parseExercises(reader, complexId)
                else -> throw IllegalStateException("Unknown field $fieldName in a Complex")
            }
        }

        reader.endObject()
        return title to exercises
    }

    private fun parseExercises(
        reader: JsonReader,
        complexId: Int
    ): List<Exercise> {
        val exercises = mutableListOf<Exercise>()
        reader.beginArray()

        var exerciseId = 8 * complexId
        while (reader.hasNext()) {
            exercises.add(parseExercise(reader, exerciseId++))
        }

        reader.endArray()
        return exercises
    }

    private fun parseExercise(
        reader: JsonReader,
        exerciseId: Int
    ): Exercise {
        var title: String? = null
        var imageRes = 0
        var setsCount = 0
        var repetitions: Int? = null
        var workoutTime: Milliseconds? = null
        var restTime: Milliseconds = 15_000L

        reader.beginObject()

        while (reader.hasNext()) {
            when (val fieldName = reader.nextName()) {
                "title" -> title = reader.nextString()
                "sets" -> setsCount = reader.nextInt()
                "repetitions" -> repetitions = reader.nextInt()
                "workoutTime" -> workoutTime = reader.nextLong()
                "restTime" -> restTime = reader.nextLong()
                else -> throw IllegalStateException("Unknown field $fieldName in a Complex")
            }
        }

        reader.endObject()
        return Exercise(
            id = exerciseId,
            title = title ?: throw IllegalArgumentException("No title in exercise"),
            imageRes = imageResourceFromString(appContext, title, R.drawable.exercise_image_placeholder),
            sets = List(setsCount) { index ->
                // CharlieDebug: Use rest time
                Set(8 * exerciseId + index, repetitions, workoutTime)
            }
        )
    }
}