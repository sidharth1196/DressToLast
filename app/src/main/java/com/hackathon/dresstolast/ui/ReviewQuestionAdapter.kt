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
import com.hackathon.dresstolast.model.ReviewQuestions

class ReviewQuestionAdapter(): RecyclerView.Adapter<ReviewQuestionAdapter.ReviewQuestionViewHolder>() {
    private var questionList: List<ReviewQuestions> = listOf()
    private var onItemClickListener: ((Boolean) -> Unit)? = null
    private var onFinishClickListener: (() -> Unit)? = null

    inner class  ReviewQuestionViewHolder(private val binding: ReviewQuestionItemBinding): RecyclerView.ViewHolder(binding.root) {
        lateinit var questions: ReviewQuestions
        fun bindToView(questions: ReviewQuestions) {
            binding.tvQuestion.text = questions.question
            binding.ivQuestion.setImageResource(questions.imageUrl)
            binding.tvQuestionGuide.text = when(questions.isSingleAnswer) {
                true -> "Select One"
                else -> "Select One or Multiple"
            }
            questions.options.forEach {
                val c = LayoutInflater.from(binding.root.context).inflate(R.layout.choice_chip, binding.chipGroup, false) as Chip
                val chip = Chip(binding.root.context)
                chip.text = it
                c.text = it
                c.setOnClickListener {
                    onItemClickListener?.invoke(questions.isSingleAnswer)
                }
                binding.chipGroup.addView(c)
            }
            binding.chipGroup.isSingleSelection = questions.isSingleAnswer
            binding.btnFinish.visibility = if (questions.isSingleAnswer) {
                View.GONE
            } else {
                View.VISIBLE
            }
            if (!questions.isSingleAnswer) {
                binding.btnFinish.setOnClickListener {
                    onFinishClickListener?.invoke()
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

    fun setQuestions(list: List<ReviewQuestions>) {
        questionList = list
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(onItemClickListener: ((Boolean) -> Unit)?) {
        this.onItemClickListener = onItemClickListener
    }

    fun setOnFinishClickListener(onFinishClickListener: (() -> Unit)?) {
        this.onFinishClickListener = onFinishClickListener
    }

}