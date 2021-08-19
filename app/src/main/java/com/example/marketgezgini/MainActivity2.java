package com.example.marketgezgini;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    ArrayList<Deal> arrayList = new ArrayList<>();
    RecyclerView recyclerView;
    ImageView imageView;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        recyclerView = findViewById(R.id.recyclerView);
        imageView = findViewById(R.id.imageView);
        title =findViewById(R.id.title);

        Intent getValue = getIntent();

        String id = getValue.getStringExtra("id");

        String image = getValue.getStringExtra("image");

        String price = getValue.getStringExtra("price");

        String[] products  = price.split("/");

        for(String s: products){

            Deal deal = new Deal("",id,image,s);

            arrayList.add(deal);
        }

        title.setText(id);

        Picasso.get().load(image).into(imageView);

        CustomAdapter customAdapter = new CustomAdapter(arrayList);

        recyclerView.setAdapter(customAdapter);

    }
}