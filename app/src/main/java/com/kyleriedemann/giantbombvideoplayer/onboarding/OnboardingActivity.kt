package com.kyleriedemann.giantbombvideoplayer.onboarding

import android.graphics.Color
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.github.paolorotolo.appintro.AppIntro
import com.github.paolorotolo.appintro.AppIntroFragment
import com.github.paolorotolo.appintro.model.SliderPage
import com.kyleriedemann.giantbombvideoplayer.R


class OnboardingActivity : AppIntro() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // todo get assets for these intro pages
        val explanationPage = SliderPage()
        explanationPage.title = "Video App"
        explanationPage.description = "This is what the app is"
        explanationPage.imageDrawable = R.drawable.ic_launcher
        explanationPage.bgColor = ContextCompat.getColor(this, R.color.primary_light)
        addSlide(AppIntroFragment.newInstance(explanationPage))

        val authPage = SliderPage()
        authPage.title = "Boxee Auth"
        authPage.description = "Make this a fragment that will launch a custom tab to auth"
        authPage.imageDrawable = R.drawable.ic_launcher
        authPage.bgColor = ContextCompat.getColor(this, R.color.primary_light)
        addSlide(AppIntroFragment.newInstance(authPage))

        // OPTIONAL METHODS
        // Override bar/separator color.
        setBarColor(Color.parseColor("#3F51B5"))
        setSeparatorColor(Color.parseColor("#2196F3"))

        // Hide Skip/Done button.
        showSkipButton(true)
        isProgressButtonEnabled = true
    }

    override fun onSkipPressed(currentFragment: Fragment?) {
        super.onSkipPressed(currentFragment)
    }

    override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
    }

    override fun onSlideChanged(oldFragment: Fragment?, newFragment: Fragment?) {
        super.onSlideChanged(oldFragment, newFragment)
    }
}
