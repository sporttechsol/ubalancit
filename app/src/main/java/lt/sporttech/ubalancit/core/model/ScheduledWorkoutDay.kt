package lt.sporttech.ubalancit.core.model

data class ScheduledWorkoutDay(
    val dayOfWeek: DayOfWeek,
    val complexes: List<Complex>,
)