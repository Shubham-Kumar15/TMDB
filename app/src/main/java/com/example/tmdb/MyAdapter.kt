package com.example.tmdb

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MyAdapter(private var context:Context,private var list:List<Movies>,private  var itemClicked: OnItemClicked): RecyclerView.Adapter<MyAdapter.MovieTab>() {
    public class MovieTab(item:View,onItemClicked: OnItemClicked) : RecyclerView.ViewHolder(item){
        var image:ImageView=item.findViewById(R.id.imageView);
        var text1:TextView=item.findViewById(R.id.textViewMain);
        var text2:TextView=item.findViewById(R.id.textView2);
        init {
            item.setOnClickListener(View.OnClickListener {
                onItemClicked.onClick(adapterPosition);
            })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieTab {
        var item=LayoutInflater.from(parent.context).inflate(R.layout.row,parent,false);

        return MovieTab(item,itemClicked);
    }

    override fun onBindViewHolder(holder: MovieTab, position: Int) {
        val m=list.get(position);
        holder.text1.setText(m.original_title);
        holder.text2.setText(m.overview);
        val te:String=TMDB.baseImage+m.poster_path;
        Glide.with(context).load(te).into(holder.image);
        //Log.e("Image Loading API",te);
    }

    override fun getItemCount(): Int {
        return list.size;
    }


    public  interface OnItemClicked{
        fun onClick(pos:Int);
    }
}