package br.com.sankhya.network;

import br.com.sankhya.network.response.UserResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserLoadService {


    @GET("users?page=")
    Call<UserResponse> getCountPage(@Query("numberpage") int numberpage);

    @GET("users?page=")
    Call<UserResponse> getUsers(@Query("numberpage") int numberpage);
}
