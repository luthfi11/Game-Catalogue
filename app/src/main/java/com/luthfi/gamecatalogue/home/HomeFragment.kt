package com.luthfi.gamecatalogue.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import com.luthfi.gamecatalogue.R
import com.luthfi.gamecatalogue.core.data.Resource
import com.luthfi.gamecatalogue.core.domain.model.Game
import com.luthfi.gamecatalogue.core.ui.GameAdapter
import com.luthfi.gamecatalogue.core.utils.OnGameClick
import com.luthfi.gamecatalogue.detail.GameDetailActivity
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(), OnGameClick {

    private val homeViewModel: HomeViewModel by viewModel()

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
            val popularGameAdapter = GameAdapter(this)
            val upcomingGameAdapter = GameAdapter(this)
            val topRatedGamesAdapter = GameAdapter(this)

            setUpRecycler(rvPopularGame, popularGameAdapter)
            setUpRecycler(rvUpcomingGame, upcomingGameAdapter)
            setUpRecycler(rvTopRatedGame, topRatedGamesAdapter)

            homeViewModel.popularGames.observe(viewLifecycleOwner, dataObserver(popularGameAdapter, progressPopular))
            homeViewModel.upcomingGames.observe(viewLifecycleOwner, dataObserver(upcomingGameAdapter, progressUpcoming))
            homeViewModel.topRatedGames.observe(viewLifecycleOwner, dataObserver(topRatedGamesAdapter, progressTopRated))
        }
    }

    private fun dataObserver(adapter: GameAdapter, progressBar: ShimmerFrameLayout) =
        Observer<Resource<List<Game>>> { game ->
            if (game != null) {
                when (game) {
                    is Resource.Loading -> progressBar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        progressBar.visibility = View.GONE
                        adapter.setData(game.data)
                    }
                    is Resource.Error -> {
                        progressBar.visibility = View.INVISIBLE
                        Toast.makeText(context, game.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    private fun setUpRecycler(recyclerView: RecyclerView, gameAdapter: GameAdapter) {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = gameAdapter
        }
    }

    override fun goToDetail(id: Int?) {
        val intent = Intent(context, GameDetailActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }
}