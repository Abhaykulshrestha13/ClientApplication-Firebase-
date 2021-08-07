package com.a13hay.clientapplication.view

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.a13hay.clientapplication.R

class ViewHolderQuotes(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var txtView: TextView = itemView.findViewById(R.id.m_txt)
}