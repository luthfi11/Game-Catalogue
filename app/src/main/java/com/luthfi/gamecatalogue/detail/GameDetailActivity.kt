package com.luthfi.gamecatalogue.detail

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.luthfi.gamecatalogue.R
import com.luthfi.gamecatalogue.core.domain.model.Game
import kotlinx.android.synthetic.main.activity_game_detail.*
import kotlinx.android.synthetic.main.content_game_detail.*
import org.koin.android.viewmodel.ext.android.viewModel

class GameDetailActivity : AppCompatActivity() {

    private val gameDetailViewModel: GameDetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_detail)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val game = intent.getParcelableExtra<Game>("game")
        showGameDetail(game)
    }

    private fun showGameDetail(game: Game?) {
        game?.let { data ->
            var favoriteStatus = data.isFavorite
            setFavoriteStatus(favoriteStatus)

            Glide.with(applicationContext).load(data.backgroundImage).into(imgBackground)
            tvGameName.text = data.name

            fab.setOnClickListener {
                favoriteStatus = !favoriteStatus
                gameDetailViewModel.setFavoriteGame(data, favoriteStatus)
                setFavoriteStatus(favoriteStatus)
            }
        }
    }

    private fun setFavoriteStatus(status: Boolean) {
        if (status) {
            fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_star_24))
        } else {
            fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_star_border_24))
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) onBackPressed()
        return super.onOptionsItemSelected(item)
    }
}