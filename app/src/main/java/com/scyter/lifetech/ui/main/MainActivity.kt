package com.scyter.lifetech.ui.main

import android.os.Bundle
import android.transition.Fade
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.scyter.lifetech.Dependencies
import com.scyter.lifetech.R
import com.scyter.lifetech.presentation.MainActivityViewModel
import com.scyter.lifetech.presentation.MainActivityViewModelFactory
import com.scyter.lifetech.ui.DetailsTransition
import com.scyter.lifetech.ui.products.ProductDetailsFragment
import com.scyter.lifetech.ui.products.ProductsFragment
import kotlinx.android.synthetic.main.product_item.view.*


class MainActivity : AppCompatActivity() {

    private val viewModelFactory: MainActivityViewModelFactory =
        Dependencies.createMainActivityViewModelFactory()

    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(MainActivityViewModel::class.java)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ProductsFragment.newInstance())
                .commitNow()
        }

    }

    fun showProductDetailsFragment(productId: String, view: View) {

        val previousFragment: Fragment =
            supportFragmentManager.findFragmentById(R.id.container) ?: return
        val nextFragment = ProductDetailsFragment.newInstance()

        previousFragment.sharedElementReturnTransition = DetailsTransition()
        previousFragment.exitTransition = Fade()

        nextFragment.sharedElementEnterTransition = DetailsTransition()
        nextFragment.enterTransition = Fade()

        val transitionName = getString(R.string.transition_name)
        view.image.transitionName = transitionName + "image"
        view.text_name.transitionName = transitionName + "name"
        view.text_price.transitionName = transitionName + "price"
        supportFragmentManager
            .beginTransaction()
            .addSharedElement(view.image, transitionName + "image")
            .addSharedElement(view.text_name, transitionName + "name")
            .addSharedElement(view.text_price, transitionName + "price")
            .replace(R.id.container, nextFragment, productId)
            .addToBackStack(ProductDetailsFragment.getTag())
            .commit()
    }
}
