package lt.sporttech.ubalancit.features.workout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import lt.sporttech.ubalancit.R

class WorkoutFragment: Fragment() {

    private lateinit var viewModel: WorkoutViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
            .get(WorkoutViewModel::class.java)
        viewModel.provideDependencies(requireContext().applicationContext)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = ComposeView(requireContext()).apply {
        viewModel.loadData()

        setContent {
            WorkoutUi(
                state = viewModel.state.value,
                submitResult = { result ->
                    viewModel.submitResult(result, ::showResultPercentage)
                }
            )
        }
    }

    private fun showResultPercentage(percentage: Int) {
        findNavController().navigate(
            R.id.showResult,
            bundleOf("percent" to percentage)
        )
    }
}