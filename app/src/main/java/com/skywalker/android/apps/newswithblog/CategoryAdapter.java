package com.skywalker.android.apps.newswithblog;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.categoryViewHolder> {

    List<Posts> data;
    Context context;


    public CategoryAdapter(List<Posts> data ,Context context){
        this.context = context;
        this.data = data;

    }


    @NonNull
    @Override
    public categoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.category,parent,false);


        return new categoryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull categoryViewHolder holder, int position) {
        Posts news = data.get(position);
        holder.CategoryName.setText(news.getCategory());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class categoryViewHolder extends RecyclerView.ViewHolder {

        TextView CategoryName;

        public categoryViewHolder(@NonNull View itemView) {
            super(itemView);

            CategoryName = itemView.findViewById(R.id.categoryName);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Posts newsData = data.get(getAdapterPosition());
                    Intent i = new Intent(context,MainActivity.class);
                    i.putExtra("idss",newsData.getId());

                    context.startActivity(i);

                }
            });

        }
    }
}
