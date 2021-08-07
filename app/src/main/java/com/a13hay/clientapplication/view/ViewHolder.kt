package com.a13hay.clientapplication.view

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.a13hay.clientapplication.R

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var mImage:ImageView = itemView.findViewById(R.id.m_image)
    var mDownload:Button = itemView.findViewById(R.id.download)
    var mProgrssBar:ProgressBar = itemView.findViewById(R.id.progress_bar)
}