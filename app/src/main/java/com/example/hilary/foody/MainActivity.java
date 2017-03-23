package com.example.hilary.foody;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ArrayList<FoodPost> foodData;
    RecyclerView foodReclerView;
    LinearLayoutManager  mLayoutManager;
    FoodListAdapter mAdapter;
    int[] images;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        images = new int[]{R.drawable.carrot, R.drawable.chicken, R.drawable.dania,
                           R.drawable.malimau, R.drawable.nyama_choma, R.drawable.nyama_choma};
        foodData = new ArrayList<FoodPost>();
        foodData.add(new FoodPost("Rice", "food for chinese", images[0]));
        foodData.add(new FoodPost("women", "food for thought", images[1]));
        foodData.add(new FoodPost("soccer", "food for men", images[2]));
        foodData.add(new FoodPost("Rap", "food for gangsters", images[3]));
        foodData.add(new FoodPost("octopus", "food for kaligraph", images[4]));
        foodData.add(new FoodPost("ugali", "ya weruya", images[5]));
        foodReclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        // in content do not change the layout size of the RecyclerView
        foodReclerView .setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        foodReclerView .setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)

        mAdapter = new FoodListAdapter(foodData);

        foodReclerView.setAdapter(mAdapter);

    }
}
