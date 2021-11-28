package lt.sporttech.ubalancit.features.workout

import android.content.Context
import lt.sporttech.ubalancit.core.model.DayOfWeek
import lt.sporttech.ubalancit.core.model.ScheduledWorkoutDay
import lt.sporttech.ubalancit.data.db.EntitiesMapper
import lt.sporttech.ubalancit.data.db.WorkoutDatabase
import lt.sporttech.ubalancit.data.parser.PlanParser
import javax.inject.Inject

class WorkoutRepository @Inject constructor(
    private val parser: PlanParser,
    private val appContext: Context,
    private val entitiesMapper: EntitiesMapper,
) {

    fun getData(dayOfWeek: DayOfWeek): ScheduledWorkoutDay {
        val plan = parser.parse()

        val dao = WorkoutDatabase
            .provide(appContext)
            .dao

        // dao.addOrUpdate(plan.workoutWeeks)

        return ScheduledWorkoutDay(
            dayOfWeek,
            plan.workoutWeeks[0].days[0].complexes,
        )
    }
}