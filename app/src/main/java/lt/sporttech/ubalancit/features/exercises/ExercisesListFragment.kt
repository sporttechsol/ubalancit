package lt.sporttech.ubalancit.features.exercises

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import lt.sporttech.ubalancit.R

class ExercisesListFragment: Fragment() {

    private lateinit var viewModel: ExercisesListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
            .get(ExercisesListViewModel::class.java)
        viewModel.provideDependencies(requireContext().applicationContext)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = ComposeView(requireContext()).apply {
        setContent {
            ExercisesListUi(
                viewModel.state.value,
                ::navigateToWorkout
            )
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.loadData()
    }

    private fun navigateToWorkout() {
        findNavController().navigate(R.id.startWorkout)
    }
}