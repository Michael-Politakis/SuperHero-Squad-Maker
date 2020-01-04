package com.renzard.superherosquadmaker.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.renzard.superherosquadmaker.R
import com.renzard.superherosquadmaker.data.db.list.CharacterListSimpleEntry
import com.renzard.superherosquadmaker.data.db.selected.SelectedCharacters
import com.renzard.superherosquadmaker.ui.base.ScopedFragment
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.character_list_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class CharacterListFragment : ScopedFragment(), KodeinAware {

    override val kodein by closestKodein()
    private val viewModelFactory: CharacterListViewModelFactory by instance()

    private lateinit var viewModel: CharacterListViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.character_list_fragment, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(CharacterListViewModel::class.java)

        selectedUI()

        bindUI()


    }

    private fun bindUI() = launch(Dispatchers.Main) {

        val characters = viewModel.characters.await()
        characters.observe(this@CharacterListFragment, Observer { charactersEntries ->
            if (charactersEntries == null) return@Observer
            group_loading.visibility = View.GONE
            initRecyclerView(charactersEntries.toCharacterItems())
        })
    }

    private fun List<CharacterListSimpleEntry>.toCharacterItems(): List<CharacterItem> {
        return this.map {
            CharacterItem(it)
        }
    }

    private fun initRecyclerView(items: List<CharacterItem>) {

        val groupAdapter = GroupAdapter<GroupieViewHolder>().apply {
            addAll(items)
        }


        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@CharacterListFragment.context)
            adapter = groupAdapter
        }

        groupAdapter.setOnItemClickListener { item, view ->
            (item as? CharacterItem)?.let {
                showCharacterDetail(it.characterEntry.characterId, view)
            }
        }
    }

    private fun showCharacterDetail(characterId: Int, view: View) {
        val actionDetail = CharacterListFragmentDirections.chosenCharacter((characterId))
        Navigation.findNavController(view).navigate(actionDetail)
    }

    private fun selectedUI() = launch(Dispatchers.Main) {

        val characters = viewModel.selectedCharacters.await()
        characters.observe(this@CharacterListFragment, Observer { charactersEntries ->
            if (charactersEntries == null) return@Observer
            if (charactersEntries.count() > 0) {
                initCharactersSelectedRecycler(charactersEntries.toSelectedItems())
            }
        })
    }


    private fun initCharactersSelectedRecycler(items: List<SelectedItems>) {

        val groupAdapter = GroupAdapter<GroupieViewHolder>().apply {
            addAll(items)
        }


        recycler_selected.apply {
            layoutManager = LinearLayoutManager(
                this@CharacterListFragment.context,
                RecyclerView.HORIZONTAL,
                false
            )
            adapter = groupAdapter
        }

        groupAdapter.setOnItemClickListener { item, view ->
            (item as? SelectedItems)?.let {
                showCharacterDetail(it.selectedCharacters.characterId, view)
            }
        }
    }

    private fun List<SelectedCharacters>.toSelectedItems(): List<SelectedItems> {
        return this.map {
            SelectedItems(it)
        }
    }


}

