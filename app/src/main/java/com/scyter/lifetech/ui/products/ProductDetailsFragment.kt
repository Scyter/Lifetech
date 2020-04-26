package com.scyter.lifetech.ui.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.scyter.lifetech.Dependencies
import com.scyter.lifetech.R
import com.scyter.lifetech.databinding.ProductDetailsFragmentBinding
import com.scyter.lifetech.presentation.ProductDetailsFragmentViewModel
import com.scyter.lifetech.presentation.ProductDetailsFragmentViewModelFactory


class ProductDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = ProductDetailsFragment()
        fun getTag() = "ProductDetailsFragment"
    }

    private val factory: ProductDetailsFragmentViewModelFactory =
        Dependencies.createProductDetailsFragmentViewModelFactory()


    private lateinit var viewModel: ProductDetailsFragmentViewModel
    private lateinit var binding: ProductDetailsFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil
            .inflate(inflater, R.layout.product_details_fragment, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, factory)
            .get(ProductDetailsFragmentViewModel::class.java)
        binding.viewModel = viewModel
        binding.image.transitionName = getString(R.string.transition_name) + "image"
        binding.textName.transitionName = getString(R.string.transition_name) + "name"
        binding.textPrice.transitionName = getString(R.string.transition_name) + "price"
    }
}