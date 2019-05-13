package com.yirong.videolist.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.item_recycle.view.*
import android.view.LayoutInflater
import android.view.View
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.yirong.videolist.R
import com.yirong.videolist.app.GlideApp


class MyReGifAdapter(val mContext: Context,var mDataLists:ArrayList<DataBean>): androidx.recyclerview.widget.RecyclerView.Adapter<MyReGifAdapter.ViewHolder>() {

    override fun onCreateViewHolder(holderView: ViewGroup, position: Int): MyReGifAdapter.ViewHolder {
        val view = LayoutInflater.from(holderView.context).inflate(R.layout.item_recycle, holderView, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {

        return mDataLists.size
    }

    override fun onBindViewHolder(holder: MyReGifAdapter.ViewHolder, position: Int) {
        Log.i("Adapter",position.toString())
        GlideApp.with(mContext).asGif().load(mDataLists[position].mImageUrl).transform(CenterCrop(),RoundedCorners(4)).placeholder(R.drawable.placeholer).diskCacheStrategy(DiskCacheStrategy.NONE).into(holder.imageView)
        holder.textView.text = "["+position+"]"+mDataLists[position].mDetailText
    }

    inner class ViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView = itemView.iv
        var textView: TextView = itemView.text
    }

data class DataBean(var mImageUrl:String,var mDetailText:String)
}