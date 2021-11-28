package lt.sporttech.ubalancit.logo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LogoViewModel: ViewModel() {

    private var timerEllapseListener: (() -> Unit)? = null

    fun whenTimerEllapses(listener: () -> Unit) {
        val shouldRunTimer = (timerEllapseListener == null)
        timerEllapseListener = listener

        if (shouldRunTimer) {
            viewModelScope.launch(Dispatchers.Main) {
                delay(1500L)
                timerEllapseListener?.invoke()
            }
        }
    }
}