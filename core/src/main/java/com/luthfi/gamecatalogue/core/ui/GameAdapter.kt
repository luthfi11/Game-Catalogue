package com.luthfi.gamecatalogue.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.luthfi.gamecatalogue.core.R
import com.luthfi.gamecatalogue.core.domain.model.Game
import com.luthfi.gamecatalogue.core.utils.OnGameClick
import kotlinx.android.synthetic.main.item_game.view.*

class GameAdapter(private val onGameClick: OnGameClick) : RecyclerView.Adapter<GameAdapter.GameViewHolder>() {

    private var gameList = arrayListOf<Game>()

    fun setData(newGameList: List<Game>?) {
        if (newGameList == null) return
        gameList.clear()
        gameList.addAll(newGameList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder =
        GameViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_game, parent, false)
        )

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.bind(gameList[position])
    }

    override fun getItemCount(): Int = gameList.size

    inner class GameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(game: Game) {
            with(itemView) {
                Glide.with(context).load(game.backgroundImage).placeholder(android.R.color.darker_gray).into(imgBackground)
                tvGameName.text = game.name

                setOnClickListener { onGameClick.goToDetail(game.id) }
            }
        }
    }

}