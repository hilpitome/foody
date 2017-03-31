package com.example.hilary.foody.adapters;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.hilary.foody.Food;
import com.example.hilary.foody.R;
import com.google.firebase.database.Query;

import java.util.ArrayList;

/**
 * Created by hilary on 3/31/17.
 */

public class MyFirebaseAdapter extends FirebaseRecyclerAdapter<MyFirebaseAdapter.ViewHolder, Food> {

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView description;

        public ViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.foodTitle);
            description = (TextView) view.findViewById(R.id.description);
        }
    }

    public MyFirebaseAdapter(Query query, Class<Food> foodClass, @Nullable ArrayList<Food> items,
                             @Nullable ArrayList<String> keys) {
        super(query, items, keys);
    }

    @Override public MyFirebaseAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.food_list_item, parent, false);

        return new ViewHolder(view);
    }



    @Override public void onBindViewHolder(MyFirebaseAdapter.ViewHolder holder, int position) {
        Food item = getItem(position);
        holder.title.setText(item.getTitle());
        holder.description.setText(String.valueOf(item.getDescription()));
    }

    @Override protected void itemAdded(Food item, String key, int position) {
        Log.d("MyAdapter", "Added a new item to the adapter.");
    }

    @Override protected void itemChanged(Food oldItem, Food newItem, String key, int position) {
        Log.d("MyAdapter", "Changed an item.");
    }

    @Override protected void itemRemoved(Food item, String key, int position) {
        Log.d("MyAdapter", "Removed an item from the adapter.");
    }

    @Override protected void itemMoved(Food item, String key, int oldPosition, int newPosition) {
        Log.d("MyAdapter", "Moved an item.");
    }
}