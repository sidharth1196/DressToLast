package com.hackathon.dresstolast.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.hackathon.dresstolast.R
import com.hackathon.dresstolast.databinding.FragmentReviewQuestionsBinding
import com.hackathon.dresstolast.model.ReviewQuestions

/**
 * A simple [Fragment] subclass.
 */
class ReviewQuestionsFragment : Fragment() {

    private lateinit var binding: FragmentReviewQuestionsBinding
    lateinit var parentActivity: MainActivity
    private lateinit var adapter: ReviewQuestionAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_review_questions, container, false)
        parentActivity = activity as MainActivity
        setupToolbar()
        initRecyclerView()
        initListeners()
        return binding.root
    }

    private fun initListeners() {

    }

    private fun initRecyclerView() {
        val imgRes = resources.getIdentifier("q1", "drawable", context?.packageName)
        adapter = ReviewQuestionAdapter()
        binding.vpReviewQuestions.adapter = adapter
        val list = mutableListOf<ReviewQuestions>(
            ReviewQuestions(
                1,
                "Did you follow the care instructions?",
                imgRes,
                listOf("Yes", "No"),
                true
            ),
            ReviewQuestions(
                2,
                "How old is the garment?",
                imgRes,
                listOf("More than 3 years", "Between 1-3 years", "Less than a year", "I don't know, the garment is second hand/vintage"),
                false
            )
        )
        list.forEach {
            it.imageUrl
        }
        adapter.setQuestions(list)
        adapter.setOnItemClickListener {
            val viewPager = binding.vpReviewQuestions
            if (it) {
                viewPager.currentItem = viewPager.currentItem + 1
            }
        }
        adapter.setOnFinishClickListener {
            findNavController().navigate(R.id.action_reviewQuestionsFragment_to_homeFragment)
        }
    }

    private fun setupToolbar() {
        parentActivity.binding.toolbarTitle.text = "Review Questions"
    }
}