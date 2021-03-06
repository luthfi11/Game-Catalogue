package com.luthfi.gamecatalogue.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.luthfi.gamecatalogue.R
import com.luthfi.gamecatalogue.core.data.Resource
import com.luthfi.gamecatalogue.core.domain.model.Game
import com.luthfi.gamecatalogue.core.ui.ScreenshotAdapter
import com.luthfi.gamecatalogue.core.utils.dateFormat
import kotlinx.android.synthetic.main.activity_game_detail.*
import kotlinx.android.synthetic.main.content_game_detail.*
import org.koin.android.viewmodel.ext.android.viewModel

class GameDetailActivity : AppCompatActivity() {

    private val gameDetailViewModel: GameDetailViewModel by viewModel()
    private lateinit var screenshotAdapter: ScreenshotAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_detail)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val gameId = intent.getIntExtra("id", 0).toString()

        screenshotAdapter = ScreenshotAdapter()
        rvScreenshot.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = screenshotAdapter
        }

        getGameDetail(gameId)
    }

    private fun getGameDetail(id: String) {
        gameDetailViewModel.getGameDetail(id).observe(this, { game ->
            when (game) {
                is Resource.Loading -> {
                    contentDetail.visibility = View.GONE
                    progressDetail.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    contentDetail.visibility = View.VISIBLE
                    progressDetail.visibility = View.GONE
                    showGameDetail(game.data)
                }
                is Resource.Error -> {
                    contentDetail.visibility = View.VISIBLE
                    progressDetail.visibility = View.GONE
                    Toast.makeText(this, game.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun showGameDetail(game: Game?) {
        game?.let { data ->
            var favoriteStatus = data.isFavorite
            setFavoriteStatus(favoriteStatus)

            Glide.with(applicationContext).load(data.backgroundImage)
                .placeholder(android.R.color.darker_gray).into(imgBackground)
            tvGameName.text = data.name
            tvDescription.text = if (data.description != "") data.description else "No description"
            tvReleaseDate.text = dateFormat(data.released)
            tvRating.text = String.format(
                getString(R.string.rating_template),
                data.rating.toString(),
                data.ratingCount
            )

            var genre = ""
            data.genres?.forEach {
                genre += "${it.name}\n"
            }

            tvGenre.text = if (genre != "") genre else "-"

            screenshotAdapter.setData(data.screenshot)

            fab.setOnClickListener {
                favoriteStatus = !favoriteStatus
                gameDetailViewModel.setFavoriteGame(data, favoriteStatus)
                setFavoriteStatus(favoriteStatus)
            }

            if (data.website != "") {
                tvWebsite.text = data.website
                tvWebsite.setOnClickListener {
                    val browseIntent = Intent(Intent.ACTION_VIEW, Uri.parse(data.website))
                    startActivity(browseIntent)
                }
            } else tvWebsite.text = "-"
        }
    }

    private fun setFavoriteStatus(status: Boolean) {
        if (status) {
            fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_star_24))
        } else {
            fab.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_baseline_star_border_24
                )
            )
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) onBackPressed()
        return super.onOptionsItemSelected(item)
    }
}