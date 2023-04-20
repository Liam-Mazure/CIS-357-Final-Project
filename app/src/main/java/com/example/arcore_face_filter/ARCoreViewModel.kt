package com.example.arcore_face_filter

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Rect
import android.os.Environment
import android.os.Handler
import android.provider.MediaStore
import android.view.PixelCopy
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.google.ar.sceneform.ux.ArFragment
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class ARCoreViewModel: ViewModel() {

    var recentPicture: Bitmap? = null

    fun takePicture(context: Context, arFragment: ArFragment) {
        val view = arFragment.arSceneView

        // Hide the buttons
        val takePicBtn = arFragment.requireActivity().findViewById<Button>(R.id.takePicBtn)
        val backBtn = arFragment.requireActivity().findViewById<Button>(R.id.backBtn)
        val takePicBtnVisibility = takePicBtn.visibility
        val backBtnVisibility = backBtn.visibility
        takePicBtn.visibility = View.GONE
        backBtn.visibility = View.GONE

        // Create bitmap to hold the screenshot
        val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)

        // Use PixelCopy to capture the screenshot
        PixelCopy.request(view, bitmap, { result ->
            if (result == PixelCopy.SUCCESS) {
                // Save the screenshot to external storage
                val date = SimpleDateFormat("yyyy-MM-dd_hh:mm:ss", Locale.getDefault()).format(Date())
                val displayName = "ARCore_" + date + ".jpg"
                val mimeType = "image/jpeg"
                val contentValues = ContentValues().apply {
                    put(MediaStore.Images.Media.DISPLAY_NAME, displayName)
                    put(MediaStore.Images.Media.MIME_TYPE, mimeType)
                    put(MediaStore.Images.Media.RELATIVE_PATH, "${Environment.DIRECTORY_PICTURES}/ARCore")
                }
                val resolver = context.contentResolver
                val uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
                uri?.let {
                    val outputStream = resolver.openOutputStream(it)
                    outputStream?.use { outputStream ->
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
                    }
                    recentPicture = bitmap
                    Toast.makeText(context, "Screenshot saved to Pictures/ARCore/$displayName", Toast.LENGTH_SHORT).show()
                } ?: Toast.makeText(context, "Error saving screenshot", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Error taking screenshot", Toast.LENGTH_SHORT).show()
            }

            // Show buttons
            takePicBtn.visibility = takePicBtnVisibility
            backBtn.visibility = backBtnVisibility
        }, Handler())
    }




}