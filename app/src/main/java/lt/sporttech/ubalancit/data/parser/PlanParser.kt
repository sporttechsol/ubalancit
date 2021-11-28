package lt.sporttech.ubalancit.data.parser

import android.content.Context
import android.util.JsonReader
import lt.sporttech.ubalancit.core.model.Plan
import lt.sporttech.ubalancit.core.model.WorkoutWeek
import java.io.InputStreamReader
import javax.inject.Inject

class PlanParser @Inject constructor(
    private val context: Context,
    private val weekParser: WorkoutWeekParser,
) {

    fun parse(): Plan {
        val workoutWeeks = context.assets.open("plan_gym_easy.json").use { istream ->
            InputStreamReader(istream).use { streamReader ->
                parseWorkoutWeeks(JsonReader(streamReader))
            }
        }

        // TODO: Parse Tests

        return Plan(workoutWeeks)
    }

    private fun parseWorkoutWeeks(reader: JsonReader): List<WorkoutWeek> {
        val weeks = mutableListOf<WorkoutWeek>()
        reader.beginObject()

        while (reader.hasNext()) {
            reader.nextName()
            weeks.add(weekParser.parse(reader))
        }

        reader.endObject()
        return weeks
    }
}