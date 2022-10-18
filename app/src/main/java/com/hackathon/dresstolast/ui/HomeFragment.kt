package com.hackathon.dresstolast.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.hackathon.dresstolast.R
import com.hackathon.dresstolast.databinding.FragmentHomeBinding
import com.hackathon.dresstolast.ui.viewModel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var brandAdapter: BrandAdapter
    lateinit var parentActivity: MainActivity
    val viewModel by viewModel<MainViewModel>()
    lateinit var searchView: SearchView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        parentActivity = activity as MainActivity
        searchView = binding.svBrand
        initRecyclerView()
        setupToolbar()
        initListeners()
        initObservers()
        initSearchView()
        return binding.root
    }

    private fun initSearchView() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val filteredList = viewModel.brands.value?.filter {
                    it.name.contains(newText.toString(), true)
                }
                brandAdapter.setBrandsList(filteredList)
                return false
            }

        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllBrands()
    }

    private fun initObservers() {
        viewModel.brands.observe(viewLifecycleOwner, Observer {
            brandAdapter.setBrandsList(it)
        })
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
    }
}