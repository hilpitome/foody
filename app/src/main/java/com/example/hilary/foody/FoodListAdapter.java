package com.example.hilary.foody;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by hilary on 22/03/17.
 */


    public class  FoodListAdapter extends RecyclerView.Adapter<FoodListAdapter.ViewHolder> {
        private ArrayList<FoodPost> mDataset;

        public FoodListAdapter(ArrayList<FoodPost> mDataset){
            this.mDataset = mDataset;
        }

        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder
        public class ViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            public ImageView foodPhoto;
            public TextView subheading;
            public TextView txtFooter;

            public ViewHolder(View v) {
                super(v);
                foodPhoto = (ImageView) v.findViewById(R.id.foodPhoto);
                subheading = (TextView) v.findViewById(R.id.subheading);
                txtFooter = (TextView) v.findViewById(R.id.description);
            }
        }


        // Create new views (invoked by the layout manager)
        @Override
        public FoodListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {

            // create a new view
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_item, parent, false);
            // set the view's size, margins, paddings and layout parameters
            ViewHolder  vh = new ViewHolder(v);


            return vh;
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            holder.subheading.setText(mDataset.get(position).subheading);
            holder.txtFooter.setText(mDataset.get(position).txtFooter);
        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return mDataset.size();
        }

    }

