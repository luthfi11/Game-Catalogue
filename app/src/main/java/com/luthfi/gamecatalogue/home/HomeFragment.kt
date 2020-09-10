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
    private lateinit var popularGameAdapter: GameAdapter
    private lateinit var upcomingGameAdapter: GameAdapter
    private lateinit var topRatedGamesAdapter: GameAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            popularGameAdapter = GameAdapter()
            popularGameAdapter.onItemClick = { goToDetail(it.id) }

            upcomingGameAdapter = GameAdapter()
            upcomingGameAdapter.onItemClick = { goToDetail(it.id) }

            topRatedGamesAdapter = GameAdapter()
            topRatedGamesAdapter.onItemClick = { goToDetail(it.id) }

            getPopularGames()
            getUpcomingGames()
            getTopRatedGames()

            with(rvPopularGame) {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = popularGameAdapter
            }

            with(rvUpcomingGame) {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = upcomingGameAdapter
            }

            with(rvTopRatedGame) {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = topRatedGamesAdapter
            }
        }
    }

    private fun getPopularGames() {
        homeViewModel.popularGames.observe(viewLifecycleOwner, { game ->
            if (game != null) {
                when (game) {
                    is Resource.Loading -> progressPopular.visibility = View.VISIBLE
                    is Resource.Success -> {
                        progressPopular.visibility = View.GONE
                        popularGameAdapter.setData(game.data)
                    }
                    is Resource.Error -> {
                        progressPopular.visibility = View.INVISIBLE
                        Toast.makeText(context, game.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    private fun getUpcomingGames() {
        homeViewModel.upcomingGames.observe(viewLifecycleOwner, { game ->
            if (game != null) {
                when (game) {
                    is Resource.Loading -> progressUpcoming.visibility = View.VISIBLE
                    is Resource.Success -> {
                        progressUpcoming.visibility = View.GONE
                        upcomingGameAdapter.setData(game.data)
                    }
                    is Resource.Error -> {
                        progressUpcoming.visibility = View.INVISIBLE
                        Toast.makeText(context, game.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    private fun getTopRatedGames() {
        homeViewModel.topRatedGames.observe(viewLifecycleOwner, { game ->
            if (game != null) {
                when (game) {
                    is Resource.Loading -> progressTopRated.visibility = View.VISIBLE
                    is Resource.Success -> {
                        progressTopRated.visibility = View.GONE
                        topRatedGamesAdapter.setData(game.data)
                    }
                    is Resource.Error -> {
                        progressTopRated.visibility = View.INVISIBLE
                        Toast.makeText(context, game.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    private fun goToDetail(id: Int?) {
        val intent = Intent(context, GameDetailActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }
}