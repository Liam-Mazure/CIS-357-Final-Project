package com.example.arcore_face_filter

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.viewModels
import com.example.arcore_face_filter.R.id.takePicBtn
import com.google.ar.core.ArCoreApk
import com.google.ar.core.CameraConfig
import com.google.ar.core.CameraConfigFilter
import com.google.ar.core.Session
import com.google.ar.core.exceptions.UnavailableUserDeclinedInstallationException

class MainActivity : AppCompatActivity() {

    val vm: MainActivityViewModel by viewModels()
    lateinit var imageView: ImageView
    //lateinit var takePicBtn: Button
    val REQUEST_IMAGE_CAPTURE = 100


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Enable AR-related functionality on ARCore supported devices only.
       //maybeEnableArButton()

        val logoutBtn = findViewById<Button>(R.id.logoutButton)
        imageView = findViewById(R.id.cameraImageView)


        logoutBtn.setOnClickListener {
            val toLogin = Intent(this, LoginActivity::class.java)
            toLogin.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(toLogin)
            vm.logout()
        }

//        // Set a camera configuration that usese the front-facing camera.
//        val filter = CameraConfigFilter(session).setFacingDirection(CameraConfig.FacingDirection.FRONT)
//        val cameraConfig = session.getSupportedCameraConfigs(filter)[0]
//        session.cameraConfig = cameraConfig

    }

//    // requestInstall(Activity, true) will triggers installation of
//// Google Play Services for AR if necessary.
//    var mUserRequestedInstall = true
//
//    override fun onResume() {
//        super.onResume()
//
//        // Check camera permission.
//        if (!CameraPermissionHelper.hasCameraPermission(this)) {
//            CameraPermissionHelper.requestCameraPermission(this)
//            return
//        }
//
//
//        // Ensure that Google Play Services for AR and ARCore device profile data are
//        // installed and up to date.
//        try {
//            if (mSession == null) {
//                when (ArCoreApk.getInstance().requestInstall(this, mUserRequestedInstall)) {
//                    ArCoreApk.InstallStatus.INSTALLED -> {
//                        // Success: Safe to create the AR session.
//                        var mSession = Session(this)
//                    }
//                    ArCoreApk.InstallStatus.INSTALL_REQUESTED -> {
//                        // When this method returns `INSTALL_REQUESTED`:
//                        // 1. ARCore pauses this activity.
//                        // 2. ARCore prompts the user to install or update Google Play
//                        //    Services for AR (market://details?id=com.google.ar.core).
//                        // 3. ARCore downloads the latest device profile data.
//                        // 4. ARCore resumes this activity. The next invocation of
//                        //    requestInstall() will either return `INSTALLED` or throw an
//                        //    exception if the installation or update did not succeed.
//                        mUserRequestedInstall = false
//                        return
//                    }
//                }
//            }
//        } catch (e: UnavailableUserDeclinedInstallationException) {
//            // Display an appropriate message to the user and return gracefully.
//            Toast.makeText(this, "Error: " + e.localizedMessage, Toast.LENGTH_SHORT).show()
//            return
//        } catch () {
//            return  // mSession remains null, since session creation has failed.
//        }
//    }
////
////
//    fun maybeEnableArButton() {
//        val mArButton = true
//        val availability = ArCoreApk.getInstance().checkAvailability(this)
//        if (availability.isTransient) {
//            // Continue to query availability at 5Hz while compatibility is checked in the background.
//            Handler().postDelayed({
//                maybeEnableArButton()
//            }, 200)
//        }
//        if (availability.isSupported) {
//            mArButton.visibility = View.VISIBLE
//            mArButton.isEnabled = true
//        } else { // The device is unsupported or unknown.
//            mArButton.visibility = View.INVISIBLE
//            mArButton.isEnabled = false
//        }
//    }
////
////    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
////        /*if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
////            val imageBitmap = data?.extras?.get("data") as Bitmap
////            imageView.setImageBitmap(imageBitmap)
////        }
////        else {
////            super.onActivityResult(requestCode, resultCode, data)
////        }*/
////    }
////
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<String>,
//        results: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, results)
//        if (!CameraPermissionHelper.hasCameraPermission(this)) {
//            Toast.makeText(this, "Camera permission is needed to run this application", Toast.LENGTH_LONG)
//                .show()
//            if (!CameraPermissionHelper.shouldShowRequestPermissionRationale(this)) {
//                // Permission denied with checking "Do not ask again".
//                CameraPermissionHelper.launchPermissionSettings(this)
//            }
//            finish()
//        }
//    }

}

