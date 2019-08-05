package com.mastercook.retrofit;

import com.mastercook.model.RecipieData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RecipieService {

    @GET("{filter}")
    Call<RecipieData> getRecipies(@Path("filter") String filter);

}
