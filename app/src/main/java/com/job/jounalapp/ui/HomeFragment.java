package com.job.jounalapp.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.Query;
import com.job.jounalapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    @BindView(R.id.home_list)
    RecyclerView homeList;
    @BindView(R.id.home_fab)
    FloatingActionButton homeFab;
    Unbinder unbinder;

    private FirestoreRecyclerAdapter adapter;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.home_fab)
    public void onViewClicked() {
        Intent intent = new Intent(getActivity(),AddItemActivity.class);
        startActivity(intent);
    }

    private void setUpDairyList(){

        LinearLayoutManager linearLayoutManager = new
                LinearLayoutManager(getContext().getApplicationContext(), LinearLayoutManager.VERTICAL, false);

        homeList.setLayoutManager(linearLayoutManager);

        // Create a reference to the members collection
        final CollectionReference transRef = mFirestore.collection(USERSTRANSACTIONCOL);
        final Query query = transRef
                .whereEqualTo("userid",mCurrentUser.getUid())
                .orderBy("timestamp", Query.Direction.ASCENDING);
    }
    @Override
    public void onStart() {
        super.onStart();

        if (adapter != null)
            adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (adapter != null)
            adapter.stopListening();
    }

}
