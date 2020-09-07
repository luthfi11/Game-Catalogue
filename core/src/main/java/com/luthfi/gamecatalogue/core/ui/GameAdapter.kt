package com.luthfi.gamecatalogue.core.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.luthfi.gamecatalogue.core.R
import com.luthfi.gamecatalogue.core.domain.model.Game
import kotlinx.android.synthetic.main.item_game.view.*

class GameAdapter : RecyclerView.Adapter<GameAdapter.GameViewHolder>() {

    private var gameList = arrayListOf<Game>()
    var onItemClick: ((Game) -> Unit)? = null

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
        @SuppressLint("SetTextI18n")
        fun bind(game: Game) {
            with(itemView) {
                Glide.with(context).load(game.backgroundImage).into(imgBackground)
                tvGameName.text = game.name
            }
        }

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(gameList[adapterPosition])
            }
        }
    }

}