package com.luthfi.gamecatalogue.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.luthfi.gamecatalogue.core.ui.FavoriteGameAdapter
import com.luthfi.gamecatalogue.detail.GameDetailActivity
import com.luthfi.gamecatalogue.favorite.di.favoriteModule
import kotlinx.android.synthetic.main.fragment_favorite.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class FavoriteFragment : Fragment() {

    private val favoriteViewModel: FavoriteViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            loadKoinModules(favoriteModule)

            val gameAdapter = FavoriteGameAdapter()
            gameAdapter.onItemClick = {
                val intent = Intent(context, GameDetailActivity::class.java)
                intent.putExtra("id", it.id)
                startActivity(intent)
            }

            favoriteViewModel.favoriteGame.observe(viewLifecycleOwner, {
                gameAdapter.setData(it)
                tvEmptyFavorite.visibility = if (it.isNotEmpty()) View.GONE else View.VISIBLE
            })

            with(rvFavoriteGame) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = gameAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        unloadKoinModules(favoriteModule)
    }
}