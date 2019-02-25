package com.example.haveibeenpawned;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.example.haveibeenpawned.Constants.END_POINT;

public interface ISSService {

       @GET(END_POINT)
       Call<List<HaveIBeenPawnedRepo>> getRepos(@Query("domain") String domain);
}
