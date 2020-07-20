package com.jhonjto.co.zemogaapp.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.jhonjto.co.zemogaapp.R
import com.jhonjto.co.zemogaapp.adapters.ViewPagerAdapter
import com.jhonjto.co.zemogaapp.databinding.ActivityMainBinding
import com.jhonjto.co.zemogaapp.ui.main.MainViewModel.UiModel
import com.jhonjto.co.zemogaapp.ui.main.MainViewModel.UiModel.Loading
import com.jhonjto.co.zemogaapp.ui.main.MainViewModel.UiModel.RefreshPosts
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.scope.lifecycleScope
import org.koin.android.viewmodel.scope.viewModel

/**
 * Created by jhon on 18/07/2020
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by lifecycleScope.viewModel(this)

    private var viewPager: ViewPager2? = null
    private var tabLayout: TabLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.model.observe(this, Observer(::updateUi))

        initViews()
        loadAdapters()
        observers()
    }

    private fun initViews() {
        viewPager = findViewById(R.id.viewPager)
        tabLayout = findViewById(R.id.tabs)
    }

    private fun loadAdapters() {
        viewPager?.adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        TabLayoutMediator(
            tabLayout!!,
            viewPager!!,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                when (position) {
                    0 -> tab.text = "ALL"
                    1 -> tab.text = "FAVORITES"
                }
            }).attach()
    }

    private fun observers() {
        refreshPosts()
    }

    private fun updateUi(model: UiModel) {
        progress.visibility =
            if (model is Loading) View.VISIBLE else View.GONE

        when (model) {
            is RefreshPosts -> model.posts
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.refresh -> refreshPosts()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun refreshPosts() {
        viewModel.refresh()
    }
}