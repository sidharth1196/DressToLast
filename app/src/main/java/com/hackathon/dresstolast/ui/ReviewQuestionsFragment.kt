package com.hackathon.dresstolast.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.hackathon.dresstolast.R
import com.hackathon.dresstolast.databinding.FragmentReviewQuestionsBinding

/**
 * A simple [Fragment] subclass.
 */
class ReviewQuestionsFragment : Fragment() {

    private lateinit var binding: FragmentReviewQuestionsBinding
    lateinit var parentActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_review_questions, container, false)
        parentActivity = activity as MainActivity
        setupToolbar()
        return binding.root
    }

    private fun setupToolbar() {
        parentActivity.binding.toolbarTitle.text = "Review Questions"
    }
}