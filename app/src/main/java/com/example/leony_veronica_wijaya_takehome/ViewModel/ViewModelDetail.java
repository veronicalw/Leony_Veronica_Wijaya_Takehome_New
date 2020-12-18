package com.example.leony_veronica_wijaya_takehome.ViewModel;

import android.app.Application;
import android.app.Service;
import android.util.Log;

import com.example.leony_veronica_wijaya_takehome.Interface.ApiService;
import com.example.leony_veronica_wijaya_takehome.Interface.Client;
import com.example.leony_veronica_wijaya_takehome.Model.Users;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewModelDetail extends ViewModel {

    private MutableLiveData<Users> Userss;

    public void loadData(String username){
        try {
            ApiService service = Client.getClient().create(ApiService.class);

            Call<Users> userResponsesCall= service.getUserDetail(username);
            userResponsesCall.enqueue(new Callback<Users>() {
                @Override
                public void onResponse(Call<Users> call, Response<Users> response) {
                    Userss.setValue(response.body());
                }

                @Override
                public void onFailure(Call<Users> call, Throwable t) {
                    Log.e("Failure in : ",t.toString());
                }
            });
        } catch (Exception e){
            Log.d("Error in : " , String.valueOf(e));
        }
    }

    public LiveData<Users> getLiveDataDetail(){
        if (Userss == null){
            Userss = new MutableLiveData<>();
        }
        return Userss;
    }
}
