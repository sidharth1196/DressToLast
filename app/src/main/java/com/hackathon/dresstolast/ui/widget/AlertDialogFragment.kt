package com.hackathon.dresstolast.ui.widget

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.hackathon.dresstolast.R
import com.hackathon.dresstolast.databinding.FragmentAlertDialogBinding
import com.hackathon.dresstolast.model.DialogMember

class AlertDialogFragment: DialogFragment() {

    private var dialogMember: DialogMember? = null
    private lateinit var binding: FragmentAlertDialogBinding

    companion object {
        fun newInstance() = AlertDialogFragment()
    }

    fun setData(dialogMember: DialogMember) {
        this.dialogMember = dialogMember
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_alert_dialog, container, false)
        dialogMember?.apply {
            binding.tvTitle.text = title
            binding.tvBody.text = body
            binding.btnNegative.text = buttonNegativeText
            binding.btnPositive.text = buttonPositiveText
            dialog?.setCancelable(cancellable)
        }
        intiListeners()
        return binding.root
    }

    private fun intiListeners() {
        binding.btnNegative.setOnClickListener {
            if(dialogMember?.lambdaNo != {}) {
                dialogMember?.lambdaNo?.let { run(it) }
            }
            dialog?.dismiss()
        }
        binding.btnPositive.setOnClickListener {
            if(dialogMember?.lambdaYes != {}) {
                dialogMember?.lambdaYes?.let { run(it) }
            }
            dialog?.dismiss()
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        if (dialogMember?.lambdaDismiss != {}) {
            dialogMember?.lambdaDismiss?.let { run(it) }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}