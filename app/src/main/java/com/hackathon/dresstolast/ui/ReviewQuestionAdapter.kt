package com.hackathon.dresstolast.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.hackathon.dresstolast.R
import com.hackathon.dresstolast.databinding.ReviewQuestionItemBinding
import com.hackathon.dresstolast.model.ReviewQuestion

class ReviewQuestionAdapter(): RecyclerView.Adapter<ReviewQuestionAdapter.ReviewQuestionViewHolder>() {
    private var questionList: List<ReviewQuestion> = listOf()
    private var onItemClickListener: ((String, Int) -> Unit)? = null
    private var onFinishClickListener: ((List<String>) -> Unit)? = null

    inner class  ReviewQuestionViewHolder(private val binding: ReviewQuestionItemBinding): RecyclerView.ViewHolder(binding.root) {
        lateinit var questions: ReviewQuestion
        fun bindToView(questions: ReviewQuestion) {
            binding.tvQuestion.text = questions.question
            questions.imageRes?.let { binding.ivQuestion.setImageResource(it) }
            binding.chipGroup.isSingleSelection = questions.isSingleAnswer
            binding.tvQuestionGuide.text = when(questions.isSingleAnswer) {
                true -> "Select One"
                else -> "Select One or Multiple"
            }
            questions.options.forEach { option->
                val chip = LayoutInflater.from(binding.root.context).inflate(R.layout.choice_chip, binding.chipGroup, false) as Chip
                chip.text = option.label
                chip.setOnClickListener {
                    if (questions.isSingleAnswer) {
                        if (binding.chipGroup.checkedChipIds.isNotEmpty()) {
                            onItemClickListener?.invoke(questions.imageUrl, option.value)
                        }
                    }
                }
                binding.chipGroup.addView(chip)
            }
            binding.btnFinish.visibility = if (questions.isSingleAnswer) {
                View.GONE
            } else {
                View.VISIBLE
            }
            if (!questions.isSingleAnswer) {
                binding.btnFinish.setOnClickListener {
                    val list = mutableListOf<String>()
                    binding.chipGroup.checkedChipIds.forEach {
                        val chip = binding.chipGroup.findViewById<Chip>(it)
                        list.add(chip.text.toString())
                    }
                    onFinishClickListener?.invoke(list)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewQuestionViewHolder {
        val binding: ReviewQuestionItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.review_question_item, parent, false)
        return ReviewQuestionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReviewQuestionViewHolder, position: Int) {
        val question = questionList[position]
        holder.bindToView(question)
    }

    override fun getItemCount(): Int {
        return questionList.size
    }

    fun setQuestions(list: List<ReviewQuestion>) {
        questionList = list
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(onItemClickListener: ((String, Int) -> Unit)?) {
        this.onItemClickListener = onItemClickListener
    }

    fun setOnFinishClickListener(onFinishClickListener: ((List<String>) -> Unit)?) {
        this.onFinishClickListener = onFinishClickListener
    }

}