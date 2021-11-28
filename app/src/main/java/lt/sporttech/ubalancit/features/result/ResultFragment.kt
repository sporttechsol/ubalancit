package lt.sporttech.ubalancit.features.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import lt.sporttech.ubalancit.R

class ResultFragment: Fragment() {

    private lateinit var viewModel: ResultViewModel

    companion object {
        private const val KEY_RESULT_PERCENTAGE = "percent"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
            .get(ResultViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = ComposeView(requireContext()).apply {
        val percentage = requireArguments().getInt(KEY_RESULT_PERCENTAGE)
        setContent {
            ResultUi(
                percentage,
                viewModel.selectedFeelingState.value,
                viewModel::selectFeeling,
            )
        }
    }

    override fun onStart() {
        super.onStart()
        if (viewModel.shouldReset()) {
            findNavController().navigate(R.id.exercisesList)
        }
    }
}