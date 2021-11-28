package lt.sporttech.ubalancit.core.model

import java.util.Calendar

enum class DayOfWeek(
    val calendarConstant: Int,
    val title: String
) {
    MONDAY(Calendar.MONDAY, "Monday"),
    TUESDAY(Calendar.TUESDAY, "Tuesday"),
    WEDNESDAY(Calendar.WEDNESDAY, "Wednesday"),
    THURSDAY(Calendar.THURSDAY, "Thursday"),
    FRIDAY(Calendar.FRIDAY, "Friday"),
    SATURDAY(Calendar.SATURDAY, "Saturday"),
    SUNDAY(Calendar.SUNDAY, "Sunday"),
}