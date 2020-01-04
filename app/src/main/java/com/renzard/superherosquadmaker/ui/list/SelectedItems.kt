package com.renzard.superherosquadmaker.ui.list

import com.bumptech.glide.Glide
import com.renzard.superherosquadmaker.R
import com.renzard.superherosquadmaker.data.db.selected.SelectedCharacters
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.selected_characters.*

class SelectedItems(
    val selectedCharacters: SelectedCharacters
) : Item() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.apply {
            viewHolder.characterIcon()

        }
    }

    override fun getLayout() = R.layout.selected_characters

    private fun GroupieViewHolder.characterIcon() {
        Glide.with(this.containerView)
            .load(selectedCharacters.thumbnailPath + "." + selectedCharacters.thumbnailExtension)
            .thumbnail(0.1f)
            .into(selected_icon)
    }

}
