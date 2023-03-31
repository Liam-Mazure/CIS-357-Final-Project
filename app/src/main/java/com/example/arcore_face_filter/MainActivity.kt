package com.example.arcore_face_filter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val logoutBtn =findViewById<Button>(R.id.logoutButton)
        logoutBtn.setOnClickListener{
            val toLogin = Intent(this, LoginActivity::class.java)
            startActivity(toLogin)
        }

        // Enable AR-related functionality on ARCore supported devices only.
        //maybeEnableArButton()
    }

    /*fun maybeEnableArButton() {
        val availability = ArCoreApk.getInstance().checkAvailability(this)
        if (availability.isTransient) {
            // Continue to query availability at 5Hz while compatibility is checked in the background.
            Handler().postDelayed({
                maybeEnableArButton()
            }, 200)
        }
        if (availability.isSupported) {
            mArButton.visibility = View.VISIBLE
            mArButton.isEnabled = true
        } else { // The device is unsupported or unknown.
            mArButton.visibility = View.INVISIBLE
            mArButton.isEnabled = false
        }
    }

    // requestInstall(Activity, true) will triggers installation of
// Google Play Services for AR if necessary.
    var mUserRequestedInstall = true

    override fun onResume() {
        super.onResume()

        // Check camera permission.
        …

        // Ensure that Google Play Services for AR and ARCore device profile data are
        // installed and up to date.
        try {
            if (mSession == null) {
                when (ArCoreApk.getInstance().requestInstall(this, mUserRequestedInstall)) {
                    ArCoreApk.InstallStatus.INSTALLED -> {
                        // Success: Safe to create the AR session.
                        mSession = Session(this)
                    }
                    ArCoreApk.InstallStatus.INSTALL_REQUESTED -> {
                        // When this method returns `INSTALL_REQUESTED`:
                        // 1. ARCore pauses this activity.
                        // 2. ARCore prompts the user to install or update Google Play
                        //    Services for AR (market://details?id=com.google.ar.core).
                        // 3. ARCore downloads the latest device profile data.
                        // 4. ARCore resumes this activity. The next invocation of
                        //    requestInstall() will either return `INSTALLED` or throw an
                        //    exception if the installation or update did not succeed.
                        mUserRequestedInstall = false
                        return
                    }
                }
            }
        } catch (e: UnavailableUserDeclinedInstallationException) {
            // Display an appropriate message to the user and return gracefully.
            Toast.makeText(this, "TODO: handle exception " + e, Toast.LENGTH_LONG)
                .show()
            return
        } catch (…) {
            …
            return  // mSession remains null, since session creation has failed.
        }
        …
    }*/


}

