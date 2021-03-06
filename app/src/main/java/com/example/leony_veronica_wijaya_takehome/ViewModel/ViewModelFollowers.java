package com.example.leony_veronica_wijaya_takehome.ViewModel;

import android.util.Log;

import com.example.leony_veronica_wijaya_takehome.Interface.ApiService;
import com.example.leony_veronica_wijaya_takehome.Interface.Client;
import com.example.leony_veronica_wijaya_takehome.Model.UserData;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewModelFollowers extends ViewModel {
    private MutableLiveData<List<UserData>> Followers;
    public static final String API_KEY = "abba41b7bb619677fcea";
    public void loadData(String username){
        try {
            ApiService service = Client.getClient().create(ApiService.class);
            Call<List<UserData>> followingCall = service.getFollowerData(API_KEY,username);
            followingCall.enqueue(new Callback<List<UserData>>() {
                @Override
                public void onResponse(Call<List<UserData>> call, Response<List<UserData>> response) {
                    Followers.setValue(response.body());
                }

                @Override
                public void onFailure(Call<List<UserData>> call, Throwable t) {
                    Log.e("Error in : " , t.toString());
                }
            });
        } catch (Exception e){
            Log.d("Error in : " , String.valueOf(e));
        }
    }

    public LiveData<List<UserData>> getLiveDataFollowers(){
        if (Followers == null){
            Followers = new MutableLiveData<>();
        }
        return Followers;
    }
}
