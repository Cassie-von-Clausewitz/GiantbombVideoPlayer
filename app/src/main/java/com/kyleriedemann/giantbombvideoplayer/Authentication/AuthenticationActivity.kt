package com.kyleriedemann.giantbombvideoplayer.Authentication

import android.support.design.widget.Snackbar
import android.widget.EditText
import butterknife.OnClick
import com.kyleriedemann.giantbombvideoplayer.Base.ActionBar.DisplayTitle
import com.kyleriedemann.giantbombvideoplayer.Base.BaseActivity
import com.kyleriedemann.giantbombvideoplayer.Base.DependencyInjection.Components.ActivityComponent
import com.kyleriedemann.giantbombvideoplayer.GiantbombApp
import com.kyleriedemann.giantbombvideoplayer.Models.Key
import com.kyleriedemann.giantbombvideoplayer.Network.GiantbombApiClient
import com.kyleriedemann.giantbombvideoplayer.Network.ServiceGenerator
import com.kyleriedemann.giantbombvideoplayer.R
import com.kyleriedemann.giantbombvideoplayer.Utils.PrefManager
import inkapplications.android.layoutinjector.Layout
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

@DisplayTitle(R.string.app_name)
@Layout(R.layout.activity_authentication)
class AuthenticationActivity : BaseActivity() {

    private val disposables = CompositeDisposable()

    @OnClick(R.id.button_authenticaion)
    internal fun onAuthenticationClick() {
        val authCodeEditText = findViewById(R.id.edit_text_auth_code) as EditText

        val authCodeText = authCodeEditText.text.toString().trim { it <= ' ' }

        authenticate(authCodeText)

        Snackbar.make(window.decorView, GETTING_YOUR_API_KEY, Snackbar.LENGTH_LONG).show()
    }

    @OnClick(R.id.button_pref_test)
    internal fun onPrefTestClick() {
        val apiKeyTest = PrefManager.with(GiantbombApp.instance()).getString(API_KEY, "No Saved API Key")

        Snackbar.make(window.decorView, apiKeyTest, Snackbar.LENGTH_LONG).show()
    }

    fun authenticate(authCode: String) {
        val client = ServiceGenerator.createService(GiantbombApiClient::class.java)

        disposables.add(client.getApiKey(authCode, "json")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ onDataReady(it) }, { onDataError(it) }))
    }

    fun onDataReady(data: Key) {
        val apiKey = data.apiKey

        PrefManager.with(GiantbombApp.instance()).save(API_KEY, apiKey)
    }

    fun onDataError(e: Throwable) {
        Snackbar.make(window.decorView, FAILED_TO_RETRIEVE_API_KEY, Snackbar.LENGTH_LONG).show()
    }

    override fun injectSelf(component: ActivityComponent) = component.inject(this)

    companion object {

        val API_KEY = "API_KEY"
        val FAILED_TO_RETRIEVE_API_KEY = "Failed to retrieve API key"
        val GETTING_YOUR_API_KEY = "Getting your API key..."
    }
}
