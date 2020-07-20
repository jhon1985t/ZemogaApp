package com.jhonjto.co.zemogaapp.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jhonjto.co.domain.DomainUserComments
import com.jhonjto.co.zemogaapp.R
import com.jhonjto.co.zemogaapp.util.basicDiffUtil
import com.jhonjto.co.zemogaapp.util.inflate
import kotlinx.android.synthetic.main.comments.view.*

/**
 * Created by jhon on 20/07/2020
 */
class CommentsAdapter : RecyclerView.Adapter<CommentsAdapter.ViewHolder>() {

    var comments : List<DomainUserComments> by basicDiffUtil(
        emptyList(), { old, new -> old.body == new.body }
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.comments, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val posts = comments[position]
        holder.bind(posts)
    }

    override fun getItemCount(): Int = comments.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(domainUserComments: DomainUserComments) = with(itemView) {
            tvComments.text = domainUserComments.body
        }
    }
}