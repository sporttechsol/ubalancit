package lt.sporttech.ubalancit.features.result

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import lt.sporttech.ubalancit.core.model.Feeling
import java.util.*

class ResultViewModel: ViewModel() {

    val selectedFeelingState = mutableStateOf<Feeling?>(null)
    val resultTime = now()

    fun selectFeeling(feeling: Feeling) {
        selectedFeelingState.value = feeling
    }

    fun shouldReset(): Boolean {
        val now = now()

        return (now[Calendar.DATE] != resultTime[Calendar.DATE]) ||
            (now[Calendar.MONTH] != resultTime[Calendar.MONTH]) ||
            (now[Calendar.YEAR] != resultTime[Calendar.YEAR])
    }

    private fun now() = Calendar.getInstance()
}