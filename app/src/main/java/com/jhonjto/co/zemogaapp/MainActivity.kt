package com.jhonjto.co.zemogaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.jhonjto.co.zemogaapp.adapters.ViewPagerAdapter

class MainActivity : AppCompatActivity() {

    private var viewPager: ViewPager2? = null
    private var tabLayout: TabLayout? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        loadAdapters()
    }

    private fun initViews() {
        viewPager = findViewById(R.id.viewPager)
        tabLayout = findViewById(R.id.tabs)
    }

    private fun loadAdapters() {
        viewPager?.adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        TabLayoutMediator(tabLayout!!, viewPager!!, TabLayoutMediator.TabConfigurationStrategy{ tab, position ->
            when (position) {
                0 -> tab.text = "ALL"
                1 -> tab.text = "FAVORITES"
            }
        }).attach()
    }

    private fun observers() {

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.refresh -> Toast.makeText(this, "Refrescar", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }
}