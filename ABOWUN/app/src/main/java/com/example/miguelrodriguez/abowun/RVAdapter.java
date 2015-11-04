package com.example.miguelrodriguez.abowun;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Miguel Rodriguez on 31/10/2015.
 */
public class RVAdapter extends RecyclerView.Adapter<RVAdapter.BirdViewHolder> implements View.OnClickListener  {

    private View.OnClickListener listener;

    public static class BirdViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView comunName;
        TextView scientificName;
        TextView info;
        ImageView birdPhoto;


        BirdViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            comunName = (TextView)itemView.findViewById(R.id.c_name);
            scientificName = (TextView)itemView.findViewById(R.id.s_name);
            birdPhoto = (ImageView)itemView.findViewById(R.id.bird_photo);
            info= (TextView)itemView.findViewById(R.id.textViewInfo);
        }
    }

    List<Bird> birds;

    RVAdapter(List<Bird> birds){
        this.birds = birds;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public BirdViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        v.setOnClickListener(this);
        BirdViewHolder pvh = new BirdViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(BirdViewHolder birdViewHolder, int i) {
        birdViewHolder.comunName.setText(birds.get(i).comunName);
        birdViewHolder.scientificName.setText(birds.get(i).scientificName);
        birdViewHolder.birdPhoto.setImageResource(birds.get(i).photoId);
        birdViewHolder.info.setText(birds.get(i).inf);
    }

    @Override
    public int getItemCount() {
        return birds.size();
    }

    @Override
    public void onClick(View v) {
        if(listener != null)
            listener.onClick(v);
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

}
