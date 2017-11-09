package com.kyleriedemann.giantbombvideoplayer.Authentication

import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.support.customtabs.CustomTabsIntent
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.widget.EditText
import com.kyleriedemann.giantbombvideoplayer.Video.Models.Key
import com.kyleriedemann.giantbombvideoplayer.Video.Network.GiantbombApiClient
import com.kyleriedemann.giantbombvideoplayer.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_authentication.*
import org.koin.android.ext.android.inject

class AuthenticationActivity : AppCompatActivity() {

    private val disposables = CompositeDisposable()

    val sharedPrefs: SharedPreferences by inject()
    val apiClient: GiantbombApiClient by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)

        text_view_auth_link.setOnClickListener {
            // Use a CustomTabsIntent.Builder to configure CustomTabsIntent.
            // Once ready, call CustomTabsIntent.Builder.build() to create a CustomTabsIntent
            // and launch the desired Url with CustomTabsIntent.launchUrl()

            val url = getString(R.string.auth_url)
            val builder = CustomTabsIntent.Builder()
            val customTabsIntent = builder.build()
            builder.setToolbarColor(getColor(R.color.primary))
            customTabsIntent.launchUrl(this, Uri.parse(url))
        }

        button_authenticaion.setOnClickListener {
            val authCodeEditText = findViewById<EditText>(R.id.edit_text_auth_code)
            val authCodeText = authCodeEditText.text.toString().trim { it <= ' ' }

            Snackbar.make(linearLayout, GETTING_YOUR_API_KEY, Snackbar.LENGTH_LONG).show()
            authenticate(authCodeText)
        }

        button_pref_test.setOnClickListener {
            val apiKeyTest = sharedPrefs.getString(API_KEY, "No Saved API Key")

            Snackbar.make(linearLayout, apiKeyTest, Snackbar.LENGTH_LONG).show()
        }
    }

    fun authenticate(authCode: String) {
        disposables.add(apiClient.getApiKey(authCode, "json")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ onDataReady(it) }, { onDataError(it) }))
    }

    fun onDataReady(data: Key) = sharedPrefs.edit().putString(API_KEY, data.apiKey).apply()

    fun onDataError(e: Throwable) = Snackbar.make(window.decorView, FAILED_TO_RETRIEVE_API_KEY, Snackbar.LENGTH_LONG).show()

    companion object {

        val API_KEY = "API_KEY"
        val FAILED_TO_RETRIEVE_API_KEY = "Failed to retrieve API key"
        val GETTING_YOUR_API_KEY = "Getting your API key..."
    }
}
