package com.example.arcore_face_filter

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.ar.core.*
import com.google.ar.core.exceptions.CameraNotAvailableException
import com.google.ar.sceneform.FrameTime
import com.google.ar.sceneform.Node
import com.google.ar.sceneform.Scene
import com.google.ar.sceneform.math.Vector3
import com.google.ar.sceneform.ux.ArFragment


class ARCoreActivity : AppCompatActivity(), Scene.OnUpdateListener {
    private lateinit var arFragment: ArFragment
    private lateinit var session: Session

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_arcore)

        val backBtn = findViewById<Button>(R.id.backBtn)
        backBtn.setOnClickListener {
            finish()
        }

        //Initialize the ARCore session
        session = Session(this)

        //Configure the ARCore Session
        val filter = CameraConfigFilter(session).setFacingDirection(CameraConfig.FacingDirection.FRONT)
        val cameraConfig = session.getSupportedCameraConfigs(filter)[0]
        session.cameraConfig = cameraConfig

        //Configs
        val config = Config(session)
        config.planeFindingMode = Config.PlaneFindingMode.HORIZONTAL_AND_VERTICAL
        config.updateMode = Config.UpdateMode.LATEST_CAMERA_IMAGE
        config.focusMode = Config.FocusMode.AUTO
        config.lightEstimationMode = Config.LightEstimationMode.AMBIENT_INTENSITY
        config.cloudAnchorMode = Config.CloudAnchorMode.DISABLED
        config.depthMode = Config.DepthMode.DISABLED
        config.augmentedFaceMode = Config.AugmentedFaceMode.DISABLED //Change this later
        config.instantPlacementMode = Config.InstantPlacementMode.DISABLED
        session.configure(config)


        // Set up AR fragment and add update listener
        arFragment = supportFragmentManager.findFragmentById(R.id.ar_fragment) as ArFragment
        arFragment.arSceneView.scene.addOnUpdateListener(this)

        // Show plane discovery controller and disable instruction view
        arFragment.planeDiscoveryController.apply {
            show()
            setInstructionView(null)
        }

        // Show plane visualizations
        arFragment.arSceneView.planeRenderer.isVisible = true
        arFragment.arSceneView.planeRenderer.isEnabled = true
        arFragment.planeDiscoveryController.show()
        arFragment.planeDiscoveryController.setInstructionView(null)

        // Resume ARCore session
        try {
            session.resume()
        } catch (e: CameraNotAvailableException) {
            Log.e(TAG, "Failed to start camera", e)
        }
    }

    override fun onUpdate(frameTime: FrameTime) {
        val arFrame = arFragment.arSceneView.arFrame
        if (arFrame != null) {
            val planes = session.getAllTrackables(Plane::class.java)
            planes.forEach { plane ->
                if (plane.trackingState == TrackingState.TRACKING) {
                    val centerPose = plane.centerPose
                    val centerNode = Node()
                    centerNode.setParent(arFragment.arSceneView.scene)
                    centerNode.localPosition = Vector3(centerPose.tx(), centerPose.ty(), centerPose.tz())
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        arFragment.arSceneView.pause()
        session.pause()
    }

    override fun onResume() {
        super.onResume()
        arFragment.arSceneView.resume()
        session.resume()
    }

    override fun onDestroy() {
        super.onDestroy()
        session.close()
    }
}

