package com.hackathon.dresstolast.ui.widget

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.hackathon.dresstolast.R
import com.hackathon.dresstolast.databinding.FragmentSimpleDialogBinding

class SimpleDialogFragment: DialogFragment() {
    private lateinit var binding: FragmentSimpleDialogBinding
    private var title = ""
    private var body = ""
    private var button = ""
    private var btnClick: (() -> Unit)? = null

    companion object {
        fun newInstance() = SimpleDialogFragment()
    }

    fun setData(title: String, body: String, button: String, btnClick: () -> Unit) {
        this.title = title
        this.body = body
        this.button = button
        this.btnClick = btnClick

    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_simple_dialog, container, false)
        populateData()
        return binding.root
    }

    private fun populateData() {
        binding.tvDialogTitle.text = title
        binding.tvBodyDialog.text = body
        binding.btnYes.text = button
        binding.btnYes.setOnClickListener {
            btnClick?.invoke()
            dialog?.dismiss()
        }
    }

    override fun onResume() {
        super.onResume()
        dialog?.window?.setLayout(
            resources.displayMetrics.widthPixels - resources.getDimensionPixelSize(R.dimen.padding_2),
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }
}