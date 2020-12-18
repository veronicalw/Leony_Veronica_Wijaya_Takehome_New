package com.example.leony_veronica_wijaya_takehome.View.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ProgressBar;
import android.widget.SearchView;

import com.example.leony_veronica_wijaya_takehome.Adapter.Adapters;
import com.example.leony_veronica_wijaya_takehome.Interface.ApiService;
import com.example.leony_veronica_wijaya_takehome.Interface.Client;
import com.example.leony_veronica_wijaya_takehome.Model.UserData;
import com.example.leony_veronica_wijaya_takehome.R;
import com.example.leony_veronica_wijaya_takehome.ViewModel.ViewModelSearch;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.Objects;

public class SearchActivity extends AppCompatActivity {
    private Adapters adapters;
    private ViewModelSearch searchViews;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private FloatingActionButton floatingActionButton;
    int num = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        progressBar = findViewById(R.id.progressbar_user);
        progressBar.setVisibility(View.INVISIBLE);

        LinearLayoutManager mLayoutManager;
        mLayoutManager = new LinearLayoutManager(this);
        floatingActionButton = findViewById(R.id.loadMore);

        recyclerView = findViewById(R.id.rv_user);
        setView();
        recyclerView.setLayoutManager(mLayoutManager);

        Objects.requireNonNull(getSupportActionBar()).setElevation(0);
        searchViews = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(ViewModelSearch.class);
        searchViews.getLiveDataSearch().observe(this, new Observer<List<UserData>>() {
            @Override
            public void onChanged(List<UserData> userData) {
                adapters = new Adapters(getApplicationContext(), userData);
                recyclerView.setAdapter(adapters);
                showLoading(false);
            }
        });

    }

    public void setView(){
        SearchView searchView = findViewById(R.id.search_user);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                searchViews.loadData(s);
                showLoading(true);
                progressBar.setVisibility(View.VISIBLE);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }

    private void showLoading(Boolean value){
        if (value){
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}