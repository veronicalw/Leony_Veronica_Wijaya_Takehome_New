package com.example.leony_veronica_wijaya_takehome.Interface;

import com.example.leony_veronica_wijaya_takehome.Model.ResponsesMessage;
import com.example.leony_veronica_wijaya_takehome.Model.UserData;
import com.example.leony_veronica_wijaya_takehome.Model.Users;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("")
    Call<String> STRING_CALL(
            @Query("page") int page,
            @Query("limit") int limit
    );

    @GET("search/users")
    Call<ResponsesMessage> getSearch(
            @Query("q") String username
    );

    @GET("users/{username}")
    Call<Users> getUserDetail(@Path("username") String username);


    @GET("users/{username}/followers")
    Call<List<UserData>> getFollowerData(
            @Header("Authorization") String authorization,
            @Path("username") String username
    );

    @GET("users/{username}/following")
    Call<List<UserData>> getFollowingData(
            @Header("Authorization") String authorization,
            @Path("username") String username
    );
}
