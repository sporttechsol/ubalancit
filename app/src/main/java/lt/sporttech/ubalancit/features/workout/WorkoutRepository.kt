package lt.sporttech.ubalancit.features.workout

import lt.sporttech.ubalancit.core.model.DayOfWeek
import lt.sporttech.ubalancit.core.model.ScheduledWorkoutDay
import lt.sporttech.ubalancit.data.parser.PlanParser
import javax.inject.Inject

class WorkoutRepository @Inject constructor(
    private val parser: PlanParser,
) {

    fun getData(dayOfWeek: DayOfWeek): ScheduledWorkoutDay {
        val plan = parser.parse()
        return ScheduledWorkoutDay(
            dayOfWeek,
            plan.workoutWeeks[0].days[0].complexes,
        )
    }
}