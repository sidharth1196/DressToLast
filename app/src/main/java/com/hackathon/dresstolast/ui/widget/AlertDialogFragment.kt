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
import com.hackathon.dresstolast.ui.viewModel.AlertDialogViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class AlertDialogFragment: DialogFragment() {

    private var dialogMember: DialogMember? = null
    private lateinit var binding: FragmentAlertDialogBinding
    val viewModel by viewModel<AlertDialogViewModel>()

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
        dialogMember?.brand?.name?.let { viewModel.getBrandByID(it) }
        dialogMember?.apply {
            binding.tvTitle.text = title
            binding.tvBody.text = body
            binding.btnNegative.text = buttonNegativeText
            binding.btnPositive.text = buttonPositiveText
            binding.tvPointsBody.text = reviewText
            reviewSum?.let {
                val durability = calculateDurability(it.toDouble())
                binding.tvIndex.text = durability.capitalize()
                val imageRes = resources.getIdentifier("index_$durability", "drawable", context?.packageName)
                binding.ivIndex.setImageResource(imageRes)
            }
            dialog?.setCancelable(cancellable)
        }
        intiListeners()
        initObservers()
        return binding.root
    }

    private fun initObservers() {
        viewModel.brand.observe(viewLifecycleOwner, androidx.lifecycle.Observer { it ->
            it?.let { brand ->
                binding.tvBrand.text = brand.name
                binding.tvBrandReviews.text = brand.reviews.toString()
                val durability = calculateDurability(brand.durabilityIndex)
                binding.tvBrandIndex.text = durability.capitalize()
                val imageRes =
                    resources.getIdentifier("index_$durability", "drawable", context?.packageName)
                binding.ivBrandIndex.setImageResource(imageRes)
            }
        })
    }

    private fun calculateDurability(score: Double): String {
        return when {
            score < 5.1 -> "fragile"
            score < 8.1 -> "reliable"
            else -> "durable"
        }
    }

    private fun intiListeners() {
        binding.btnNegative.setOnClickListener {

        }
        binding.btnPositive.setOnClickListener {
            if(dialogMember?.lambdaYes != {}) {
                dialogMember?.lambdaYes?.let { run(it) }
            }
            dialog?.dismiss()
        }
        binding.imageView3.setOnClickListener {
            if(dialogMember?.lambdaNo != {}) {
                dialogMember?.lambdaNo?.let { run(it) }
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