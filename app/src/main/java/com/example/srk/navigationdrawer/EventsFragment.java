package com.example.srk.navigationdrawer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EventsFragment extends Fragment {

    private   RecyclerView mrecycler;
    public   static FirebaseDatabase mfirebaseDatabase;
    private DatabaseReference mdatabaseRef;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_events,container,false);

        //Initiate RecyclerView
        mrecycler = (RecyclerView)view.findViewById(R.id.myrecycler);
        mrecycler.setHasFixedSize(true);
        mrecycler.setLayoutManager(new LinearLayoutManager(getActivity()));



        mfirebaseDatabase = FirebaseDatabase.getInstance();
        mdatabaseRef= mfirebaseDatabase.getReference("Data");
        mdatabaseRef.keepSynced(true);



        FirebaseRecyclerAdapter<GetData_From_FireBase,Viewholder_Firebase_Events> firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<GetData_From_FireBase, Viewholder_Firebase_Events>(GetData_From_FireBase.class,
                R.layout.eventsrecyclerlayout, Viewholder_Firebase_Events.class,mdatabaseRef) {
            @Override
            protected void populateViewHolder(Viewholder_Firebase_Events viewHolder, GetData_From_FireBase model, int position) {
                viewHolder.setDetails(getActivity(),model.getTitle(),model.getDescription(),model.getImage(),model.getTime());
            }

            @Override
            public Viewholder_Firebase_Events onCreateViewHolder(ViewGroup parent, int viewType) {
                Viewholder_Firebase_Events h=super.onCreateViewHolder(parent,viewType);

                return h;
            }
        };

        mrecycler.setAdapter(firebaseRecyclerAdapter);



    return view;
    }


}
