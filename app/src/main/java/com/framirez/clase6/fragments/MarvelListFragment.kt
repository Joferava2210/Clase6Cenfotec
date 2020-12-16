package com.framirez.clase6.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import com.framirez.clase6.R
import com.framirez.clase6.adapters.MarvelListAdapter
import com.framirez.clase6.db.CharacterEntity
import com.framirez.clase6.viewmodels.CreateCharacterViewModel
import com.framirez.clase6.viewmodels.MarvelListViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_marvel_list.*
import java.util.*

class MarvelListFragment : Fragment() {

    private val viewModel: MarvelListViewModel by viewModels()
    private val viewModelCreate: CreateCharacterViewModel by viewModels()
    private val adapter = MarvelListAdapter { character ->
        findNavController().navigate(R.id.action_marvelListFragment_to_bottomMenuFragment)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel.getCharacterList()
        return inflater.inflate(R.layout.fragment_marvel_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rcv_marvel.adapter = adapter
        rcv_marvel.addItemDecoration(DividerItemDecoration(requireContext(), VERTICAL))

        viewModel.getCharacterListResponse().observe(viewLifecycleOwner){ characterList ->
            adapter.characterList = characterList
            rcv_marvel.visibility = View.VISIBLE
        }

        viewModel.getIsLoading().observe(viewLifecycleOwner) { isLoading ->
            pb_loading.visibility = if(isLoading) View.VISIBLE else View.GONE
        }

        viewModel.getIsError().observe(viewLifecycleOwner){ isError ->
            Snackbar.make(fr_parent, R.string.error_text , Snackbar.LENGTH_LONG).show()
        }

        rcv_marvel.setOnClickListener {
            viewModelCreate.insertCharacter(CharacterEntity(UUID.randomUUID().toString(), "Felipe", "No sé qué poner", "menos"))
        }

    }
}