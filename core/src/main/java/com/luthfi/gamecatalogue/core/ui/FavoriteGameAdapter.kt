package com.luthfi.gamecatalogue.core.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.luthfi.gamecatalogue.core.R
import com.luthfi.gamecatalogue.core.domain.model.Game
import kotlinx.android.synthetic.main.item_favorite_game.view.*

class FavoriteGameAdapter : RecyclerView.Adapter<FavoriteGameAdapter.FavoriteGameViewHolder>() {

    private var gameList = arrayListOf<Game>()
    var onItemClick: ((Game) -> Unit)? = null

    fun setData(newGameList: List<Game>?) {
        if (newGameList == null) return
        gameList.clear()
        gameList.addAll(newGameList)
        notifyDataSetChanged()
    }

    fun clearData() {
        gameList.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteGameViewHolder =
        FavoriteGameViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_favorite_game, parent, false)
        )

    override fun onBindViewHolder(holder: FavoriteGameViewHolder, position: Int) {
        holder.bind(gameList[position])
    }

    override fun getItemCount(): Int = gameList.size

    inner class FavoriteGameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bind(game: Game) {
            with(itemView) {
                Glide.with(context).load(game.backgroundImage).placeholder(android.R.color.darker_gray).into(imgBackground)
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