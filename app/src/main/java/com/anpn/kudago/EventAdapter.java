package com.anpn.kudago;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;


import com.google.gson.JsonObject;


import com.google.gson.JsonArray;
import com.squareup.picasso.Picasso;


import java.util.List;


public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {

    private final RecyclerViewInterface recyclerViewInterface;


    Context context;
    List<Events> eventsList;


    public EventAdapter(Context context, List<Events> events,
                        RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        eventsList = events;
        this.recyclerViewInterface = recyclerViewInterface;

    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new EventViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {

        Events events = eventsList.get(position);
        holder.name.setText(events.getTitle());


        JsonArray jsonArray = events.getImages();

        String value = null;
        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject json = jsonArray.get(i).getAsJsonObject();
            value = json.get("image").getAsString();

        }

        Context context = holder.image.getContext();
        Picasso.with(context).load(value).into(holder.image);


    }

    @Override
    public int getItemCount() {
        return eventsList.size();
    }

    public static class EventViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        ImageView image;

        public EventViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            name = itemView.findViewById(R.id.tvTitle);
            image = itemView.findViewById(R.id.imEvents);

            itemView.setOnClickListener(v -> {

                if (recyclerViewInterface != null) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        recyclerViewInterface.onItemClick(pos);
                    }
                }
            });

        }
    }
}
