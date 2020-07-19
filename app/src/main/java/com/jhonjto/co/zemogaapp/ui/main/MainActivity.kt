package com.jhonjto.co.zemogaapp.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.jhonjto.co.zemogaapp.R
import com.jhonjto.co.zemogaapp.adapters.ViewPagerAdapter
import com.jhonjto.co.zemogaapp.databinding.ActivityMainBinding
import com.jhonjto.co.zemogaapp.ui.main.MainActivityViewModel.UiModel
import com.jhonjto.co.zemogaapp.ui.main.MainActivityViewModel.UiModel.Content
import com.jhonjto.co.zemogaapp.ui.main.MainActivityViewModel.UiModel.Loading
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.scope.lifecycleScope
import org.koin.android.viewmodel.scope.viewModel

/**
 * Created by jhon on 18/07/2020
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by lifecycleScope.viewModel(this)

    private var viewPager: ViewPager2? = null
    private var tabLayout: TabLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.model.observe(this, Observer(::updateUi))

        initViews()
        loadAdapters()
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

    }

    private fun updateUi(model: UiModel) {
        progress.visibility =
            if (model is Loading) View.VISIBLE else View.GONE

        when (model) {
            is Content -> model.posts
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.refresh -> Toast.makeText(this, "Refrescar", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }
}