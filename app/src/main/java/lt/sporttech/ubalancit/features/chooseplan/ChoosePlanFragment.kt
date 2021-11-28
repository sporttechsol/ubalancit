package lt.sporttech.ubalancit.features.chooseplan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import lt.sporttech.ubalancit.R

class ChoosePlanFragment: Fragment() {

    private lateinit var viewModel: ChoosePlanViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
            .get(ChoosePlanViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = ComposeView(requireContext()).apply {
        setContent {
            ChoosePlanUi(
                viewModel.state.value,
                onGymClick = viewModel::onGymClick,
                onHomeClick = viewModel::onHomeClick,
                onFullWorkoutClick = viewModel::onFullWorkoutClick,
                onFifteenMinutesClick = viewModel::onFifteenMinutesClick,
                onContinueClick = ::onSubmit,
            )
        }
    }

    private fun onSubmit() {
        viewModel.onSubmit()
        findNavController().navigate(R.id.proceedFromChoosePlan)
    }
}