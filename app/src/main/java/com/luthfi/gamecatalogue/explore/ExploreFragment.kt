package com.luthfi.gamecatalogue.explore

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.luthfi.gamecatalogue.R
import com.luthfi.gamecatalogue.core.data.Resource
import com.luthfi.gamecatalogue.core.ui.FavoriteGameAdapter
import com.luthfi.gamecatalogue.core.utils.OnGameClick
import com.luthfi.gamecatalogue.detail.GameDetailActivity
import kotlinx.android.synthetic.main.fragment_explore.*
import org.koin.android.viewmodel.ext.android.viewModel

class ExploreFragment : Fragment(), OnGameClick, SearchView.OnQueryTextListener {

    private val exploreViewModel: ExploreViewModel by viewModel()
    private lateinit var gameAdapter: FavoriteGameAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_explore, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            gameAdapter = FavoriteGameAdapter(this)

            with(rvSearch) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = gameAdapter
            }

            exploreViewModel.searchGames.observe(viewLifecycleOwner, { game ->
                when (game) {
                    is Resource.Loading -> {
                        progressSearch.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        progressSearch.visibility = View.GONE
                        if (game.data != null) {
                            gameAdapter.setData(game.data)
                            tvEmpty.visibility = View.GONE
                        } else tvEmpty.visibility = View.VISIBLE
                    }
                    is Resource.Error -> {
                        progressSearch.visibility = View.GONE
                        Toast.makeText(context, game.message, Toast.LENGTH_SHORT).show()
                    }
                }
            })

            search.setOnQueryTextListener(this)
        }
    }

    override fun goToDetail(id: Int?) {
        val intent = Intent(context, GameDetailActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) {
            gameAdapter.clearData()
            exploreViewModel.setSearchQuery(query)
        }
        return true
    }

    override fun onQueryTextChange(p0: String?): Boolean = false
}