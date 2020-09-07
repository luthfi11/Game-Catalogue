package com.luthfi.gamecatalogue.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.luthfi.gamecatalogue.R
import com.luthfi.gamecatalogue.core.data.Resource
import com.luthfi.gamecatalogue.core.ui.GameAdapter
import com.luthfi.gamecatalogue.detail.GameDetailActivity
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()
    private lateinit var gameAdapter: GameAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            gameAdapter = GameAdapter()
            gameAdapter.onItemClick = {
                val intent = Intent(context, GameDetailActivity::class.java)
                intent.putExtra("game", it)
                startActivity(intent)
            }

            getGameData()

            swipeHome.setOnRefreshListener {
                getGameData()
            }

            with(rvPopularGame) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = gameAdapter
            }
        }
    }

    private fun getGameData() {
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
    }
}