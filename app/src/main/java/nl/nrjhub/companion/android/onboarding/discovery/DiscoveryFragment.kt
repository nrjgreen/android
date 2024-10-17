package nl.nrjhub.companion.android.onboarding.discovery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import nl.nrjhub.companion.android.R
import nl.nrjhub.companion.android.onboarding.OnboardingViewModel
import nl.nrjhub.companion.android.onboarding.authentication.AuthenticationFragment
import nl.nrjhub.companion.android.onboarding.manual.ManualSetupFragment
import nl.nrjhub.companion.android.util.compose.HomeAssistantAppTheme
import javax.inject.Inject
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DiscoveryFragment @Inject constructor() : Fragment() {

    private val viewModel by activityViewModels<OnboardingViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.onDiscoveryActive()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        lifecycle.addObserver(viewModel.homeAssistantSearcher)

        return ComposeView(requireContext()).apply {
            setContent {
                HomeAssistantAppTheme {
                    DiscoveryView(
                        onboardingViewModel = viewModel,
                        manualSetupClicked = { navigateToManualSetup() },
                        instanceClicked = { onInstanceClicked(it) }
                    )
                }
            }
        }
    }

    private fun navigateToManualSetup() {
        parentFragmentManager
            .beginTransaction()
            .replace(R.id.content, ManualSetupFragment::class.java, null)
            .addToBackStack(null)
            .commit()
    }

    private fun onInstanceClicked(instance: HomeAssistantInstance) {
        viewModel.manualUrl.value = instance.url.toString()
        parentFragmentManager
            .beginTransaction()
            .replace(R.id.content, AuthenticationFragment::class.java, null)
            .addToBackStack(null)
            .commit()
    }
}
