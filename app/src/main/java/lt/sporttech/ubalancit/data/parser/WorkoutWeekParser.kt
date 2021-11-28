package lt.sporttech.ubalancit.data.parser

import android.util.JsonReader
import lt.sporttech.ubalancit.R
import lt.sporttech.ubalancit.core.alias.Milliseconds
import lt.sporttech.ubalancit.core.model.Complex
import lt.sporttech.ubalancit.core.model.Exercise
import lt.sporttech.ubalancit.core.model.Set
import lt.sporttech.ubalancit.core.model.WorkoutDay
import lt.sporttech.ubalancit.core.model.WorkoutWeek
import javax.inject.Inject

class WorkoutWeekParser @Inject constructor() {

    fun parse(reader: JsonReader): WorkoutWeek {
        val days = mutableListOf<WorkoutDay>()
        reader.beginObject()

        while (reader.hasNext()) {
            reader.nextName()
            days.add(parseDay(reader))
        }

        reader.endObject()
        return WorkoutWeek(days)
    }

    private fun parseDay(reader: JsonReader): WorkoutDay {
        val complexes = mutableListOf<Complex>()
        reader.beginObject()

        while (reader.hasNext()) {
            val fieldTitle = reader.nextName()
            val (complexTitle, exercises) = parseComplex(reader)

            complexes.add(
               Complex(complexTitle ?: fieldTitle, exercises)
            )
        }

        reader.endObject()
        return WorkoutDay(complexes)
    }

    private fun parseComplex(reader: JsonReader): Pair<String?, List<Exercise>> {
        var title: String? = null
        var exercises = listOf<Exercise>()
        reader.beginObject()

        while (reader.hasNext()) {
            when (val fieldName = reader.nextName()) {
                "name" -> title = reader.nextString()
                "exercises" -> exercises = parseExercises(reader)
                else -> throw IllegalStateException("Unknown field $fieldName in a Complex")
            }
        }

        reader.endObject()
        return title to exercises
    }

    private fun parseExercises(reader: JsonReader): List<Exercise> {
        val exercises = mutableListOf<Exercise>()
        reader.beginArray()

        while (reader.hasNext()) {
            exercises.add(parseExercise(reader))
        }

        reader.endArray()
        return exercises
    }

    private fun parseExercise(reader: JsonReader): Exercise {
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
            title = title ?: throw IllegalArgumentException("No title in exercise"),
            imageRes = if (imageRes == 0) R.drawable.exercise_image_placeholder else imageRes,
            sets = List(setsCount) {
                // CharlieDebug: Use rest time
                Set(0, repetitions, workoutTime)
            }
        )
    }
}