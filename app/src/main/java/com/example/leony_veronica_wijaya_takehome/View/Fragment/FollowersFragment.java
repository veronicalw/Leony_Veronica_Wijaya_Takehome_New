package com.example.leony_veronica_wijaya_takehome.View.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.leony_veronica_wijaya_takehome.Adapter.Adapters;
import com.example.leony_veronica_wijaya_takehome.Model.UserData;
import com.example.leony_veronica_wijaya_takehome.R;
import com.example.leony_veronica_wijaya_takehome.ViewModel.ViewModelFollowers;

import java.sql.Struct;
import java.util.List;
import java.util.Objects;

import static com.example.leony_veronica_wijaya_takehome.View.Activity.DetailActivity.SEARCH_DATA;

public class FollowersFragment extends Fragment {
    private RecyclerView recyclerView;
    private Adapters adapters;
    private ProgressBar progressBar;
    private int previousTotal = 0;
    private boolean loading = true;
    private int visibleThreshold = 5;
    int pastVisiblesItems, visibleItemCount, totalItemCount;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_followers, container, false);
        recyclerView = view.findViewById(R.id.rvFollowers);

        progressBar = view.findViewById(R.id.pbFollowers);
        showLoading(true);

        LinearLayoutManager mLayoutManager;
        mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) { //check for scroll down
                    visibleItemCount = mLayoutManager.getChildCount();
                    totalItemCount = mLayoutManager.getItemCount();
                    pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();

                    if (loading) {
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                            loading = false;
                            Log.v("...", "Last Item Wow !");
                            // Do pagination.. i.e. fetch new data

                            loading = true;
                        }
                    }
                }
            }
        });
        recyclerView.setLayoutManager(mLayoutManager);

        UserData userData = Objects.requireNonNull(getActivity().getIntent().getParcelableExtra(SEARCH_DATA));

        if (userData != null){
            ViewModelFollowers followers = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(ViewModelFollowers.class);
            followers.loadData(userData.getLogin());
            followers.getLiveDataFollowers().observe(getViewLifecycleOwner(), new Observer<List<UserData>>() {
                @Override
                public void onChanged(List<UserData> userData) {
                    adapters = new Adapters(getContext(), userData);
                    recyclerView.setAdapter(adapters);
                    showLoading(false);
                }
            });
        }
        return view;
    }

    private void showLoading(Boolean value){
        if (value){
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}