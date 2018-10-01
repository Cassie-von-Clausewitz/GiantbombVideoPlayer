package com.wyrmix.giantbombvideoplayer.auth

import android.content.ClipboardManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.wyrmix.giantbombvideoplayer.R
import com.wyrmix.giantbombvideoplayer.databinding.FragmentAuthenticationBinding
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.android.Main
import kotlinx.coroutines.experimental.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.Koin.Companion.logger

class AuthenticationFragment: Fragment() {

    val authViewModel: AuthenticationViewModel by viewModel()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentAuthenticationBinding.inflate(inflater, container, false)

        binding.textViewAuthLink.setOnClickListener {
            val url = getString(R.string.auth_url)
            val builder = CustomTabsIntent.Builder()
            val customTabsIntent = builder.build()
            builder.setToolbarColor(ContextCompat.getColor(context!!, R.color.primaryColor))
            customTabsIntent.launchUrl(context, Uri.parse(url))
        }

        binding.buttonAuthenticaion.setOnClickListener { _ ->
            var authCodeText = binding.editTextAuthCode.text.toString().trim { it <= ' ' }

            if (authCodeText.isBlank()) {
                val clipboard = getSystemService<ClipboardManager>(context!!, ClipboardManager::class.java)
                authCodeText = clipboard?.primaryClip?.getItemAt(0)?.coerceToText(context).toString().trim()
                logger.info(authCodeText)
            }

            Snackbar.make(binding.root, GETTING_YOUR_API_KEY, Snackbar.LENGTH_LONG).show()

            GlobalScope.launch(Dispatchers.Main) {
                val success = authViewModel.authenticate(authCodeText)
                if (success) Snackbar.make(binding.root, GOT_YOUR_API_KEY, Snackbar.LENGTH_LONG).show()
                else Snackbar.make(binding.root, FAILED_TO_RETRIEVE_API_KEY, Snackbar.LENGTH_LONG).show()
            }
        }

        binding.buttonPrefTest.setOnClickListener {
            val apiKeyTest = authViewModel.getApiKey()

            Snackbar.make(binding.root, apiKeyTest, Snackbar.LENGTH_LONG).show()
        }

        return binding.root
    }
}
