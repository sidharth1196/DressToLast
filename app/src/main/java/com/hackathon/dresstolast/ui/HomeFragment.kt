package com.hackathon.dresstolast.ui

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
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
import com.hackathon.dresstolast.ui.viewModel.CameraViewModel
import com.hackathon.dresstolast.ui.viewModel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
private const val TAKE_PICTURE_REQ_CODE = 1001
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var brandAdapter: BrandAdapter
    lateinit var parentActivity: MainActivity
    val viewModel by viewModel<MainViewModel>()
    private val cameraViewModel by sharedViewModel<CameraViewModel>()
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
            it.forEach {
                val imageUrl = when {
                    it.durabilityIndex < 5.1 -> "index_fragile"
                    it.durabilityIndex < 8.1 -> "index_reliable"
                    else -> "index_durable"
                }
                it.imageRes = resources.getIdentifier(imageUrl, "drawable", context?.packageName)
            }
            brandAdapter.setBrandsList(it)
        })
    }

    private fun initListeners() {
        binding.btnCreateReview.setOnClickListener {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(takePictureIntent, TAKE_PICTURE_REQ_CODE)
        }
        parentActivity.binding.ivAccount.setOnClickListener {
            if (viewModel.isUserLoggedIn()) {
                findNavController().navigate(R.id.action_homeFragment_to_acountFragment)
            } else {
                findNavController().navigate(R.id.action_homeFragment_to_signInFragment)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == TAKE_PICTURE_REQ_CODE && resultCode == Activity.RESULT_OK) {
            val image = data?.extras?.get("data") as Bitmap
            cameraViewModel.imageBitmap.postValue(image)
            findNavController().navigate(R.id.action_homeFragment_to_cameraResultFragment)
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun setupToolbar() {
        parentActivity.binding.toolbarTitle.text = "Dress To Last"
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