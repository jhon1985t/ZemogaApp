package com.jhonjto.co.zemogaapp.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jhonjto.co.domain.DomainPostsItem
import com.jhonjto.co.zemogaapp.R
import com.jhonjto.co.zemogaapp.util.basicDiffUtil
import com.jhonjto.co.zemogaapp.util.inflate
import kotlinx.android.synthetic.main.posts_detail.view.*

/**
 * Created by jhon on 19/07/2020
 */
class PostsAdapter : RecyclerView.Adapter<PostsAdapter.ViewHolder>() {

    var posts : List<DomainPostsItem> by basicDiffUtil(
        emptyList(), { old, new -> old.body == new.body }
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.posts_detail, false)
        return ViewHolder(
            view
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val posts = posts[position]
        holder.bind(posts)
    }

    override fun getItemCount(): Int = posts.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(domainPostsItem: DomainPostsItem) = with(itemView) {
            when (domainPostsItem.id <= 20) {
                true -> {
                    imPostsDot.visibility = View.VISIBLE
                    tvPostsBody.text = domainPostsItem.body
                }
                false -> {
                    imPostsDot.visibility = View.INVISIBLE
                    tvPostsBody.text = domainPostsItem.body
                }
            }
        }
    }
}