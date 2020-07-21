package com.jhonjto.co.zemogaapp.adapters

import android.widget.Toast
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jhonjto.co.domain.DomainPostsItem
import com.jhonjto.co.zemogaapp.R
import com.jhonjto.co.zemogaapp.util.basicDiffUtil
import com.jhonjto.co.zemogaapp.util.inflate
import kotlinx.android.synthetic.main.post_favorites.view.*

/**
 * Created by jhon on 19/07/2020
 */
class FavoritesAdapter : RecyclerView.Adapter<FavoritesAdapter.ViewHolder>() {

    var favorites : List<DomainPostsItem> by basicDiffUtil(
        emptyList(), { old, new -> old.body == new.body }
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.post_favorites, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val posts = favorites[position]
        holder.bind(posts)
    }

    override fun getItemCount(): Int = favorites.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(domainPostsItem: DomainPostsItem) = with(itemView) {
            when (domainPostsItem.isFavorite) {
                true -> tvPostsFavorite.text = domainPostsItem.body
                else -> Toast.makeText(itemView.context, "No hay favoritos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}