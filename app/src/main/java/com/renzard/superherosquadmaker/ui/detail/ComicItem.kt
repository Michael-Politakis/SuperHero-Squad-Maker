package com.renzard.superherosquadmaker.ui.detail

import com.bumptech.glide.Glide
import com.renzard.superherosquadmaker.R
import com.renzard.superherosquadmaker.data.db.details.ComicEntry
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.comics.*

class ComicItem(
    val comics: ComicEntry
) : Item() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.apply {
            viewHolder.comicIcon()
            viewHolder.comic_name.text = comics.name

        }
    }

    override fun getLayout() = R.layout.comics

    private fun GroupieViewHolder.comicIcon() {
        Glide.with(this.containerView)
            .load(comics.resourceURI)
            .thumbnail(0.1f)
            .into(comic_display)
    }


}