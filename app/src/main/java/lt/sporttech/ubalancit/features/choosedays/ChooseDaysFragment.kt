package lt.sporttech.ubalancit.features.choosedays

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

class ChooseDaysFragment: Fragment() {

    private lateinit var viewModel: ChooseDaysViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
            .get(ChooseDaysViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = ComposeView(requireContext()).apply {
        setContent {
            ChooseDaysUi(
                state = viewModel.state.value,
                toggleSelection = viewModel::toggleSelection,
                onContinueClick = viewModel::onSubmit,
            )
        }
    }
}