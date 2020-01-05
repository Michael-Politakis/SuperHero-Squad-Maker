package com.renzard.superherosquadmaker.ui.detail

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.renzard.superherosquadmaker.R
import com.renzard.superherosquadmaker.data.db.details.ComicEntry
import com.renzard.superherosquadmaker.internal.IdNotFountException
import com.renzard.superherosquadmaker.ui.base.ScopedFragment
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.details_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.factory

class DetailsFragment : ScopedFragment(), KodeinAware {

    override val kodein by closestKodein()

    private val instanceFactory:
            ((Int) -> DetailsViewModelFactory) by factory()

    private lateinit var viewModel: DetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar?.hide()

        return inflater.inflate(R.layout.details_fragment, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val id = arguments?.let { DetailsFragmentArgs.fromBundle(it).characterId }
        try {
            val characterId = id ?: IdNotFountException()
            viewModel = ViewModelProviders.of(this, instanceFactory(characterId.toString().toInt()))
                .get(DetailsViewModel::class.java)
        } catch (e: IdNotFountException) {
            println("Character was not retrieved")
        }
        selectedCheck()
        bindUI()

        comicUI()
    }

    private fun selectedCheck() = launch(Dispatchers.Main) {

        val checkSelected = viewModel.isSelectedCharacter.await()
        checkSelected.observe(this@DetailsFragment, Observer { characterEntry ->
            if (characterEntry == null) return@Observer


            clickButton(characterEntry.characterSelected)
        })


    }

    private fun clickButton(characterSelected: Boolean) {
        if (!characterSelected) {
            chooseButton.text = getResources().getText(R.string.hire_to_squad)
            chooseButton.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.colorAccent)))

        } else {
            chooseButton.text = getResources().getText(R.string.fire_from_squad)
            chooseButton.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.chosen)))

        }
        chooseButton.setOnClickListener {
            if (!characterSelected) {
                chooseButton.text = getResources().getText(R.string.fire_from_squad)
                chooseButton.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.chosen)))
                viewModel.selectCharacter(true)

            } else {
                chooseButton.text = getResources().getText(R.string.hire_to_squad)
                chooseButton.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.colorAccent)))
                viewModel.selectCharacter(false)

            }
        }

    }


    private fun bindUI() = launch(Dispatchers.Main) {
        val characterDetail = viewModel.character.await()
        characterDetail.observe(this@DetailsFragment, Observer { characterEntry ->
            if (characterEntry == null) return@Observer
            progressBar_loading2.visibility = View.GONE
            textView_loading2.visibility = View.GONE

            characterNameDisplay.text = characterEntry.characterName

            descriptionDisplay.text = characterEntry.description
            Glide.with(this@DetailsFragment)

                .load(characterEntry.thumbnailPath + "." + characterEntry.thumbnailExtension)
                .format(DecodeFormat.PREFER_RGB_565)
                .thumbnail(0.5f)
                .into(characterPhoto)


        })
    }

    private fun comicUI() = launch(Dispatchers.Main) {

        val comics = viewModel.comics.await()
        comics.observe(this@DetailsFragment, Observer { comicEntries ->
            if (comicEntries == null) return@Observer
            initComicsRecycler(comicEntries.toComicItems())

        })
    }


    private fun initComicsRecycler(items: List<ComicItem>) {

        val groupAdapter = GroupAdapter<GroupieViewHolder>().apply {
            addAll(items)
        }


        comic_recycler.apply {
            layoutManager = GridLayoutManager(this@DetailsFragment.context, 2)
            adapter = groupAdapter
        }

    }

    private fun List<ComicEntry>.toComicItems(): List<ComicItem> {
        return this.map {
            ComicItem(it)
        }
    }


}

