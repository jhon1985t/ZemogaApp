package com.jhonjto.co.zemogaapp.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.jhonjto.co.zemogaapp.ui.posts.LoadPosts
import com.jhonjto.co.zemogaapp.ui.comments.ShowFavorites

/**
 * Created by jhon on 18/07/2020
 */
class ViewPagerAdapter internal constructor(fm : FragmentManager?, lifecycle: Lifecycle) : FragmentStateAdapter(fm!!, lifecycle) {

    private val COUNT = 2

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when(position) {
            0 -> fragment = LoadPosts()
            1 -> fragment = ShowFavorites()
        }
        return fragment!!
    }

    override fun getItemCount(): Int {
        return COUNT
    }
}