package lt.sporttech.ubalancit.data.preferences

import android.content.Context
import javax.inject.Inject

class GlobalPreferences @Inject constructor(
    private val context: Context
) {
    companion object {
        private const val PREFERENCES_NAME = "GlobalPreferences"
    }

    private val preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)


}