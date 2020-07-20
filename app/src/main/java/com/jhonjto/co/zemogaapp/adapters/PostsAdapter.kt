package com.jhonjto.co.zemogaapp.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jhonjto.co.domain.DomainPostsItem
import com.jhonjto.co.zemogaapp.R
import com.jhonjto.co.zemogaapp.ui.detail.DetailActivity
import com.jhonjto.co.zemogaapp.util.basicDiffUtil
import com.jhonjto.co.zemogaapp.util.inflate
import com.jhonjto.co.zemogaapp.util.startActivity
import kotlinx.android.synthetic.main.posts_detail.view.*

/**
 * Created by jhon on 19/07/2020
 */
class PostsAdapter(
    private val listener: (id: Int, isReaded: Boolean) -> Unit
) : RecyclerView.Adapter<PostsAdapter.ViewHolder>() {

    var posts : List<DomainPostsItem> by basicDiffUtil(
        emptyList(), { old, new -> old.body == new.body }
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.posts_detail, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val posts = posts[position]
        holder.bind(posts)
        holder.itemView.llPostsContent.setOnClickListener {
            listener(posts.id, true)
            holder.itemView.context?.startActivity<DetailActivity> {
                putExtra(DetailActivity.POST, posts.id)
            }
        }
    }

    override fun getItemCount(): Int = posts.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(domainPostsItem: DomainPostsItem) = with(itemView) {
            when (domainPostsItem.id <= 20) {
                true -> {
                    imPostsDot.visibility = View.VISIBLE
                    tvPostsBody.text = domainPostsItem.body
                    if (domainPostsItem.isReaded) {
                        imPostsDot.visibility = View.INVISIBLE
                    }
                }
                false -> {
                    imPostsDot.visibility = View.INVISIBLE
                    tvPostsBody.text = domainPostsItem.body
                }
            }
        }
    }
}