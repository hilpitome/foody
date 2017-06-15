package com.example.hilary.foody;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hilary.foody.adapters.MyFirebaseAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import java.util.ArrayList;

/**
 * Created by hilary on 6/6/17.
 */

public class LatestFoodFragment extends Fragment {
    private static final String SAVED_ADAPTER_ITEMS = "saved_adapter_items";
    private final static String SAVED_ADAPTER_KEYS = "saved_adapter_keys";
    TextView title;
    TextView price;
    TextView description;
    ImageView foodImage;
    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mFoodReference = mRootRef.child("foods");
    Query mQuery;
    ArrayList<Food> mAdapterItems;
    ArrayList<String> mAdapterKeys;
    RecyclerView mRecyclerView;
    MyFirebaseAdapter mMyAdapter;
    ProgressDialog mProgressBar;
    FirebaseAuth mAuth;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_latest_food,
                container, false);
        intitializeView(view);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.foodListRecyclerView);
        setupRecyclerview();

        return view;
    }

    public  void  intitializeView(View v){
        title = (TextView) v.findViewById(R.id.title);
        description = (TextView) v.findViewById(R.id.description);


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        Log.e("user", user.getEmail());
        mProgressBar = new ProgressDialog(getActivity());
        handleInstanceState(savedInstanceState);
        setupFirebase();

    }
    private void setupFirebase() {
        showProgress();
        mQuery = mFoodReference.limitToLast(10);
        mQuery.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                removeProgress(); //Remove progress bar when data has been fully downloaded
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
    }
    private void handleInstanceState(Bundle savedInstanceState) {

        if (savedInstanceState != null &&
            savedInstanceState.containsKey(SAVED_ADAPTER_ITEMS) &&
            savedInstanceState.containsKey(SAVED_ADAPTER_KEYS)) {
            mAdapterItems = Parcels.unwrap(savedInstanceState.getParcelable(SAVED_ADAPTER_ITEMS));
            mAdapterKeys = savedInstanceState.getStringArrayList(SAVED_ADAPTER_KEYS);
        } else {
            mAdapterItems = new ArrayList<Food>();
            mAdapterKeys = new ArrayList<String>();
        }
    }
    private void setupRecyclerview() {

        mMyAdapter = new MyFirebaseAdapter(mQuery, Food.class, mAdapterItems, mAdapterKeys);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mMyAdapter);
    };
    @Override
    public void onDestroy() {
        super.onDestroy();
        mMyAdapter.destroy();

    }
    public void showProgress(){
        mProgressBar.setMessage("please wait");
        mProgressBar.show();
    }
    public void removeProgress(){
        mProgressBar.dismiss();
    }
}
