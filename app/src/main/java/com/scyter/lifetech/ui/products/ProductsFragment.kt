package com.scyter.lifetech.ui.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import com.scyter.lifetech.Dependencies
import com.scyter.lifetech.R
import com.scyter.lifetech.databinding.ProductsFragmentBinding
import com.scyter.lifetech.presentation.ProductsFragmentViewModel
import com.scyter.lifetech.presentation.ProductsFragmentViewModelFactory
import com.scyter.lifetech.ui.adapters.ProductsAdapter
import com.scyter.lifetech.ui.main.MainActivity


class ProductsFragment : Fragment() {

    companion object {
        fun newInstance() = ProductsFragment()
    }

    private val factory: ProductsFragmentViewModelFactory =
        Dependencies.createProductsFragmentViewModelFactory()


    private lateinit var viewModel: ProductsFragmentViewModel
    private lateinit var binding: ProductsFragmentBinding

    private lateinit var productsAdapter: ProductsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil
            .inflate(inflater, R.layout.products_fragment, container, false)

        binding.productsList.layoutManager =
            GridLayoutManager(requireContext(), resources.getInteger(R.integer.numberOfColumns))
        productsAdapter = ProductsAdapter { productId, view ->
            viewModel.onProductSelected(productId)
            val activity = activity
            if (activity is MainActivity) {
                activity.showProductDetailsFragment(productId, view)
            } else {
                // show error
            }
        }
        binding.productsList.adapter = productsAdapter

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, factory)
            .get(ProductsFragmentViewModel::class.java)

        viewModel.products.observe(viewLifecycleOwner) {
            productsAdapter.submitList(it)
        }
    }
}