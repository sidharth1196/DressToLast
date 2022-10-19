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
import com.hackathon.dresstolast.model.DialogMember
import com.hackathon.dresstolast.ui.viewModel.CameraViewModel
import com.hackathon.dresstolast.ui.viewModel.MainViewModel
import com.hackathon.dresstolast.ui.widget.AlertDialogFragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class ReviewQuestionsFragment : Fragment() {

    private lateinit var binding: FragmentReviewQuestionsBinding
    lateinit var parentActivity: MainActivity
    private lateinit var adapter: ReviewQuestionAdapter
    val viewModel by viewModel<MainViewModel>()
    private val cameraViewModel by sharedViewModel<CameraViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_review_questions, container, false)
        parentActivity = activity as MainActivity
        parentActivity = activity as MainActivity
        parentActivity.setToolbarVisibility(View.GONE)
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
            val reviewSum = viewModel.calculateDurabilityIndex(cameraViewModel.brand.value)
            val dialogText: String
            val reviewText: String
            val btnYesText: String
            if (viewModel.isUserLoggedIn()) {
                dialogText = "You have just finished another durability review and your" +
                        " answers will count towards the brand’s durability score. " +
                        "Go to My account to receive your points!"
                reviewText = "You have 70 points left to the next level."
                btnYesText = "My account"
            } else {
                dialogText = "You have just finished another durability review and your " +
                        "answers will count towards the brand’s durability score. " +
                        "Sign in to receive your points and discount voucher!"
                reviewText = "First durability points to get you started."
                btnYesText = "Sign in"
            }
            val dialogFragment: AlertDialogFragment = AlertDialogFragment.newInstance()
            dialogFragment.setData(
                DialogMember(
                    title = "Congrats!",
                    body = dialogText,
                    cancellable = false,
                    buttonPositiveText = btnYesText,
                    buttonNegativeText = "See Brand Details",
                    lambdaNo = {
                        findNavController().navigate(R.id.action_reviewQuestionsFragment_to_homeFragment)
                    },
                    lambdaYes = {
                        if (viewModel.isUserLoggedIn()){
                            findNavController().navigate(R.id.action_reviewQuestionsFragment_to_accountFragment)
                        } else {
                            findNavController().navigate(R.id.action_reviewQuestionsFragment_to_signInFragment)
                        }
                    },
                    brand = cameraViewModel.brand.value,
                    reviewSum = reviewSum,
                    reviewText = reviewText
                )
            )
            dialogFragment.show(parentFragmentManager, AlertDialogFragment::class.java.simpleName)
        }
    }

    private fun setupToolbar() {
        parentActivity.binding.toolbarTitle.text = "Review Questions"
    }
}