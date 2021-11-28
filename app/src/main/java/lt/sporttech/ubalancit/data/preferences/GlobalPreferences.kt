package lt.sporttech.ubalancit.data.preferences

import android.content.Context
import androidx.core.content.edit
import lt.sporttech.ubalancit.core.model.DayOfWeek
import javax.inject.Inject

class GlobalPreferences @Inject constructor(
    context: Context
) {
    companion object {
        private const val PREFERENCES_NAME = "GlobalPreferences"
        private const val KEY_DAY_OF_WEEKS = "weekDays"
    }

    private val preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)

    var daysOfWeek: List<DayOfWeek>
        get() {
            val string = preferences.getString(KEY_DAY_OF_WEEKS, "") ?: ""
            return string.map { char ->
                DayOfWeek.values()["$char".toInt()]
            }
        }
        set(value) {
            val string = value.joinToString(separator = "") { day ->
                "${day.ordinal}"
            }

            preferences.edit {
                putString(KEY_DAY_OF_WEEKS, string)
            }
        }
}