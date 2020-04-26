package com.scyter.lifetech.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.scyter.lifetech.R
import com.scyter.lifetech.databinding.ProductItemBinding
import com.scyter.lifetech.domain.model.Product
import com.scyter.lifetech.presentation.ProductViewModel


class ProductsAdapter(val onAccountClickEvent: (String, View) -> Unit) :
    ListAdapter<Product, ProductsAdapter.ProductViewHolder>(ProductDiffCallback()) {

    private lateinit var binding: ProductItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.product_item, parent, false
        )
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ProductViewHolder(private val binding: ProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val viewModel = ProductViewModel()

        fun bind(product: Product) {
            viewModel.bind(product)
            binding.viewModel = viewModel

            itemView.setOnClickListener {
                onAccountClickEvent(product.productId, binding.root)
            }
        }
    }

    class ProductDiffCallback : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.productId == newItem.productId
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.name == newItem.name
                    && oldItem.image == newItem.image
                    && oldItem.price == newItem.price
            //&& oldItem.description == newItem.description
        }
    }
}