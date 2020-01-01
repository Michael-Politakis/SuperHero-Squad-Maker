package com.renzard.superherosquadmaker.ui.list

import com.bumptech.glide.Glide
import com.renzard.superherosquadmaker.R
import com.renzard.superherosquadmaker.data.db.list.CharacterListSimpleEntry
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_character.*

class CharacterItem(
    val characterEntry: CharacterListSimpleEntry
) : Item() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.apply {
            viewHolder.characterIcon()
            viewHolder.character_name_display.text = characterEntry.characterName

        }
    }

    override fun getLayout() = R.layout.item_character

    private fun GroupieViewHolder.characterIcon() {
        Glide.with(this.containerView)
            .load(characterEntry.thumbnailPath + "." + characterEntry.thumbnailExtension)
            .thumbnail(0.1f)
            .into(character_icon_display)
    }

}