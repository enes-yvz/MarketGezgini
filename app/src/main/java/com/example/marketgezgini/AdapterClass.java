package com.example.marketgezgini;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.MyViewHolder>{

    ArrayList<Deal> list;
    MainActivity mainActivity;

    public AdapterClass(ArrayList<Deal> list,MainActivity mainActivity){

        this.mainActivity = mainActivity;
        this.list=list;

    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_holder,viewGroup,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        final String id = list.get(position).getC();

        final String price = list.get(position).getE();

        final String image = list.get(position).getD();

        holder.id.setText(list.get(position).getC());

        Picasso.get().load(list.get(position).getD()).into(holder.image);

        if(!price.equals("")){

            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent second = new Intent(mainActivity,MainActivity2.class);

                    second.putExtra("id",id);

                    second.putExtra("price",price);

                    second.putExtra("image",image);

                    mainActivity.startActivity(second);
                }
            });

        }

        else {

            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mainActivity.filter(id);
                    
                }
            });


        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;

        TextView id;

        ImageView image;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);

            cardView = itemView.findViewById(R.id.cardView);

            id =itemView.findViewById(R.id.title);

            image =itemView.findViewById(R.id.image);
        }

    }
}
