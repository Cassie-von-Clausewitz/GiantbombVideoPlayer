package com.kyleriedemann.giantbombvideoplayer.Authentication

import android.content.SharedPreferences
import android.os.Bundle
import android.os.PersistableBundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.widget.EditText
import com.kyleriedemann.giantbombvideoplayer.Models.Key
import com.kyleriedemann.giantbombvideoplayer.Network.GiantbombApiClient
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

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_authentication)

        button_authenticaion.setOnClickListener {
            val authCodeEditText = findViewById<EditText>(R.id.edit_text_auth_code)
            val authCodeText = authCodeEditText.text.toString().trim { it <= ' ' }

            Snackbar.make(window.decorView, GETTING_YOUR_API_KEY, Snackbar.LENGTH_LONG).show()
            authenticate(authCodeText)
        }

        button_pref_test.setOnClickListener {
            val apiKeyTest = sharedPrefs.getString(API_KEY, "No Saved API Key")

            Snackbar.make(window.decorView, apiKeyTest, Snackbar.LENGTH_LONG).show()
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
