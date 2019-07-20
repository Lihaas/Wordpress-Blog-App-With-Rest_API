package com.skywalker.android.apps.newswithblog;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolde> {
 List<Posts> data;
 Context context;


 public NewsAdapter(List<Posts> data ,Context context){
     this.context = context;
     this.data = data;

 }

    @NonNull
    @Override
    public NewsViewHolde onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item,parent,false);


        return new NewsViewHolde(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolde holder, int position) {
                Posts news = data.get(position);

        Document document = Jsoup.parse(news.getDescription());
                holder.Title.setText(news.getTitle());
                holder.Disc.setText(document.text());

       // Picasso.get().load(news.getPostURL()).into(holder.image);
        Glide.with(context).load(news.postImg).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class NewsViewHolde extends RecyclerView.ViewHolder {
     TextView Title,Disc;
        ImageView image;


     public NewsViewHolde(@NonNull View itemView) {
            super(itemView);

            Disc = itemView.findViewById(R.id.discription);
            Title = itemView.findViewById(R.id.titless);
            image = itemView.findViewById(R.id.imagess);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Posts newsData = data.get(getAdapterPosition());
                    Intent i = new Intent(context,DetailActivity.class);
                    i.putExtra("URL",newsData.getPostURL());
                    i.putExtra("Image",newsData.getPostImg());
                    i.putExtra("disc",newsData.getDescription());
                    i.putExtra("title",newsData.getTitle());
                   // i.putExtra()


                    context.startActivity(i);
                }
            });

        }
    }
}
