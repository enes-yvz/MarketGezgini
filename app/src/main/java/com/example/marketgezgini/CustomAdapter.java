package com.example.marketgezgini;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    ArrayList<Deal> list;

    public CustomAdapter(ArrayList<Deal> list){

        this.list=list;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_list,viewGroup,false);

        return new CustomAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.price.setText(list.get(position).getE());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            price = itemView.findViewById(R.id.price);

        }
    }
}
