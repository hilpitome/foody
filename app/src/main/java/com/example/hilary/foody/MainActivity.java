package com.example.hilary.foody;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        foodData = new ArrayList<FoodPost>();
        foodData.add(new FoodPost("Rice", "food for chinese"));
        foodData.add(new FoodPost("women", "food for thought"));
        foodData.add(new FoodPost("soccer", "food for men"));
        foodData.add(new FoodPost("Rap", "food for gangsters"));
        foodData.add(new FoodPost("octopus", "food for kaligraph"));
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
