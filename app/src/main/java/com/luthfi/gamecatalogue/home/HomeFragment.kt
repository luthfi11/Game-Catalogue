package com.luthfi.gamecatalogue.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.luthfi.gamecatalogue.R
import com.luthfi.gamecatalogue.core.data.Resource
import com.luthfi.gamecatalogue.core.ui.GameAdapter
import com.luthfi.gamecatalogue.core.ui.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val gameAdapter = GameAdapter()
            val factory = ViewModelFactory.getInstance(requireActivity())

            homeViewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]
            homeViewModel.game.observe(viewLifecycleOwner, { game ->
                if (game != null) {
                    when(game) {
                        is Resource.Loading -> swipeHome.isRefreshing = true
                        is Resource.Success -> {
                            swipeHome.isRefreshing = false
                            gameAdapter.setData(game.data)
                        }
                        is Resource.Error -> {
                            swipeHome.isRefreshing = true
                            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })

            with(rvPopularGame) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = gameAdapter
            }
        }
    }
}