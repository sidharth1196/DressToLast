package com.hackathon.dresstolast.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import com.hackathon.dresstolast.R
import com.hackathon.dresstolast.databinding.FragmentCameraResultBinding
import com.hackathon.dresstolast.ui.viewModel.CameraViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 * A simple [Fragment] subclass.
 */
class CameraResultFragment : Fragment() {

    private lateinit var binding: FragmentCameraResultBinding
    lateinit var parentActivity: MainActivity
    private val cameraViewModel by sharedViewModel<CameraViewModel>()
    val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)
    private lateinit var inputImage: InputImage

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_camera_result, container, false)
        parentActivity = activity as MainActivity
        parentActivity = activity as MainActivity
        parentActivity.setToolbarVisibility(View.GONE)
        setupToolbar()
        initListeners()
        initObservers()
        return binding.root
    }

    private fun initObservers() {
        /*cameraViewModel.imageProxy.observe(viewLifecycleOwner, Observer { image ->
            val buffer = image.planes[0].buffer
            val bytes = ByteArray(buffer.capacity())
            buffer[bytes]
            val bitmapImage = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
            image.close()

            // Must run in UI thread
            binding.ivCapturedImage.rotation = image.imageInfo.rotationDegrees.toFloat()
            binding.ivCapturedImage.setImageBitmap(bitmapImage)
        })*/
        cameraViewModel.imageBitmap.observe(viewLifecycleOwner, Observer {
            binding.ivCapturedImage.setImageBitmap(it)
            inputImage = InputImage.fromBitmap(it, 0)
            processImage()
        })
    }

    private fun processImage() {
        val result = recognizer.process(inputImage)
            .addOnSuccessListener {
                Log.d("DTL", "Image process success, ${it.text}")
                cameraViewModel.processImageToGetBrand(it.textBlocks)
                for (block in it.textBlocks) {
                    for (line in block.lines) {
                        Log.d("DTL", "Lines: ${line .text}")
                        for (element in line.elements) {
                        }
                    }
                }
            }
            .addOnFailureListener {
                Log.d("DTL", "Image process failed, ${it.message}")
            }

        Log.d("DTL", "result: $result")
    }

    private fun initListeners() {
        binding.btnYes.setOnClickListener {
            findNavController().navigate(R.id.action_cameraResultFragment_to_reviewQuestionsFragment)
        }
    }

    private fun setupToolbar() {
        parentActivity.binding.toolbarTitle.text = "Camera Result"
    }
}