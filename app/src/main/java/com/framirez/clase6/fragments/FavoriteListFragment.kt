package com.framirez.clase6.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.framirez.clase6.R
import com.framirez.clase6.adapters.FavoriteListAdapter
import com.framirez.clase6.viewmodels.FavoriteListViewModel
import kotlinx.android.synthetic.main.fragment_favorite_list.*


class FavoriteListFragment : Fragment() {
    private val viewModel: FavoriteListViewModel by viewModels()
    private val adapter = FavoriteListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favorite_list_rcv.adapter = adapter

        viewModel.getAllProducts().observe(viewLifecycleOwner) { favoriteList ->
            adapter.chracters = favoriteList
        }
    }

}