package com.hackathon.dresstolast.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.hackathon.dresstolast.R
import com.hackathon.dresstolast.databinding.FragmentReviewQuestionsBinding
import com.hackathon.dresstolast.model.ReviewQuestion
import com.hackathon.dresstolast.ui.viewModel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class ReviewQuestionsFragment : Fragment() {

    private lateinit var binding: FragmentReviewQuestionsBinding
    lateinit var parentActivity: MainActivity
    private lateinit var adapter: ReviewQuestionAdapter
    val viewModel by viewModel<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_review_questions, container, false)
        parentActivity = activity as MainActivity
        viewModel.getAllQuestions()
        setupToolbar()
        initRecyclerView()
        initListeners()
        initObservers()
        return binding.root
    }

    private fun initObservers() {
        viewModel.questions.observe(viewLifecycleOwner, Observer {
            adapter.setQuestions(it)
        })
    }

    private fun initListeners() {

    }

    private fun initRecyclerView() {
        adapter = ReviewQuestionAdapter()
        binding.vpReviewQuestions.adapter = adapter
        adapter.setOnItemClickListener { key, value ->
            viewModel.answers[key] = value
            val viewPager = binding.vpReviewQuestions
            viewPager.currentItem = viewPager.currentItem + 1
        }
        adapter.setOnFinishClickListener {
            // viewModel.calculateDurabilityIndex()

            findNavController().navigate(R.id.action_reviewQuestionsFragment_to_homeFragment)
        }
    }

    private fun setupToolbar() {
        parentActivity.binding.toolbarTitle.text = "Review Questions"
    }
}