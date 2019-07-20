package com.skywalker.android.apps.newswithblog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class DetailActivity extends AppCompatActivity {

   ImageView main,shortimg;
   TextView titlee,discc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        String Title = getIntent().getStringExtra("title");
        String disc =  getIntent().getStringExtra("disc");
        String imageUrl = getIntent().getStringExtra("Image");

        main = findViewById(R.id.imageWebPage);
        shortimg = findViewById(R.id.mainImage);
        titlee = findViewById(R.id.mainTitle);
        discc = findViewById(R.id.mainDisc);


        Picasso.get().load(imageUrl).into(main);
        Picasso.get().load(imageUrl).into(shortimg);

        Document document = Jsoup.parse(disc);

        titlee.setText(Title);
        discc.setText(document.text());

    }
}
