package com.luthfi.gamecatalogue.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.luthfi.gamecatalogue.core.R
import com.luthfi.gamecatalogue.core.domain.model.Screenshot
import kotlinx.android.synthetic.main.item_screenshot.view.*

class ScreenshotAdapter : RecyclerView.Adapter<ScreenshotAdapter.ViewHolder>() {

    private var screenshotList = arrayListOf<Screenshot>()

    fun setData(newScreenshot: List<Screenshot>?) {
        if (newScreenshot == null) return
        screenshotList.clear()
        screenshotList.addAll(newScreenshot)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_screenshot, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(screenshotList[position])
    }

    override fun getItemCount(): Int = screenshotList.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(screenshot: Screenshot) {
            with(itemView) {
                Glide.with(context).load(screenshot.image).placeholder(android.R.color.darker_gray).into(imgScreenshot)
            }

        }
    }
}