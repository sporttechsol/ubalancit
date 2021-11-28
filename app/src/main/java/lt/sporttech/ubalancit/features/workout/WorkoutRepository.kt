package lt.sporttech.ubalancit.features.workout

import android.content.Context
import lt.sporttech.ubalancit.core.model.DayOfWeek
import lt.sporttech.ubalancit.core.model.ScheduledWorkoutDay
import lt.sporttech.ubalancit.data.db.EntitiesMapper
import lt.sporttech.ubalancit.data.db.WorkoutDatabase
import lt.sporttech.ubalancit.data.parser.PlanParser
import lt.sporttech.ubalancit.data.preferences.GlobalPreferences
import java.util.*
import javax.inject.Inject

class WorkoutRepository @Inject constructor(
    private val parser: PlanParser,
    private val appContext: Context,
    private val entitiesMapper: EntitiesMapper,
    private val preferences: GlobalPreferences,
) {

    fun shouldWorkOutToday(): Boolean {
        return today() in preferences.daysOfWeek
    }

    fun loadData(): ScheduledWorkoutDay {
        val plan = parser.parse()

        val dao = WorkoutDatabase
            .provide(appContext)
            .dao

        // dao.addOrUpdate(plan.workoutWeeks)

        return ScheduledWorkoutDay(
            today(),
            plan.workoutWeeks[0].days[0].complexes,
        )
    }

    fun saveWorkoutDays(days: List<DayOfWeek>) {
        preferences.daysOfWeek = days
    }

    private fun today(): DayOfWeek {
        val calendarConstant = Calendar.getInstance()[Calendar.DAY_OF_WEEK]
        return DayOfWeek.values().find { day ->
            day.calendarConstant == calendarConstant
        }!!
    }
}