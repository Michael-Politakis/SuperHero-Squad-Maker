package com.renzard.superherosquadmaker.ui.list

import com.bumptech.glide.Glide
import com.renzard.superherosquadmaker.R
import com.renzard.superherosquadmaker.data.db.localized.CharacterListSimpleEntry
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_character.*

class CharacterItem(
    val characterEntry: CharacterListSimpleEntry
) : Item() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.apply {
            character_name_display.text = characterEntry.characterName
            characterIcon()

        }
    }

    override fun getLayout() = R.layout.item_character

    private fun ViewHolder.characterIcon() {
        Glide.with(this.containerView)
            .load(characterEntry.thumbnailPath)
            .load(character_icon_display)
    }

}