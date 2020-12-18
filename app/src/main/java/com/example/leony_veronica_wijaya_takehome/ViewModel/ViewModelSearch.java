package com.example.leony_veronica_wijaya_takehome.ViewModel;

import android.util.Log;
import com.example.leony_veronica_wijaya_takehome.Interface.ApiService;
import com.example.leony_veronica_wijaya_takehome.Interface.Client;
import com.example.leony_veronica_wijaya_takehome.Model.ResponsesMessage;
import com.example.leony_veronica_wijaya_takehome.Model.UserData;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewModelSearch extends ViewModel {
    private MutableLiveData<List<UserData>> Users = new MutableLiveData<>();
    public static final String API_KEY = "abba41b7bb619677fcea";

    public void loadData(String username){
        try {
            ApiService service = Client.getClient().create(ApiService.class);
            Call<ResponsesMessage> call = service.getSearch(username);
            call.enqueue(new Callback<ResponsesMessage>() {
                @Override
                public void onResponse(Call<ResponsesMessage> call, Response<ResponsesMessage> response) {
                    if (response.isSuccessful() && response.body() != null){
                        List<UserData> userData = response.body().getItems();
                        Users.postValue(userData);
                    }
                }

                @Override
                public void onFailure(Call<ResponsesMessage> call, Throwable t) {
                    Log.e("Failure in : ",t.toString());
                }
            });
        } catch (Exception e){
            Log.d("Error in : " , String.valueOf(e));
        }
    }

    public LiveData<List<UserData>> getLiveDataSearch(){
        return Users;
    }
}
