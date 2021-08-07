package com.a13hay.clientapplication.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.a13hay.clientapplication.R
import com.a13hay.clientapplication.model.Model
import com.a13hay.clientapplication.model.ModelQuotes
import com.a13hay.clientapplication.view.ViewHolder
import com.a13hay.clientapplication.view.ViewHolderQuotes

class MyAdapterQuotes(context: Context, list: ArrayList<ModelQuotes>): RecyclerView.Adapter<ViewHolderQuotes>() {
    var mContext = context
    var mlist = list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderQuotes {
         var view: View = LayoutInflater.from(mContext).inflate(R.layout.item_list_txt,parent,false)
        return ViewHolderQuotes(view)
    }

    override fun onBindViewHolder(holder: ViewHolderQuotes, position: Int) {
        holder.txtView.text=(mlist[position].txtQuotes)
        Log.i("Res",mlist[position].txtQuotes)
    }

    override fun getItemCount(): Int {
        return mlist.size
    }
}