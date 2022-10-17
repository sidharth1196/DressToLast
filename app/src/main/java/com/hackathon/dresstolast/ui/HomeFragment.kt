package com.hackathon.dresstolast.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.hackathon.dresstolast.R
import com.hackathon.dresstolast.databinding.FragmentHomeBinding
import com.hackathon.dresstolast.model.Brand

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var brandAdapter: BrandAdapter
    lateinit var parentActivity: MainActivity
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        parentActivity = activity as MainActivity
        initRecyclerView()
        setupToolbar()
        initListeners()
        return binding.root
    }

    private fun initListeners() {
        binding.btnCreateReview.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_cameraFragment)
        }
    }

    private fun setupToolbar() {
        parentActivity.binding.toolbarTitle.text = "Top Brands"
    }

    private fun initRecyclerView() {
        brandAdapter = BrandAdapter()
        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvBrands.adapter = brandAdapter
        binding.rvBrands.layoutManager = layoutManager
        binding.rvBrands.addItemDecoration(
            DividerItemDecoration(
                context,
                layoutManager.orientation
            )
        )

        val list = mutableListOf(Brand(1, "Levis", "$$$", 468, 1))
        list.add(Brand(1, "Zara", "$$$", 321,1))
        list.add(Brand(1, "Weekday", "$$$", 322, 2))
        brandAdapter.setBrandsList(list)
    }
}