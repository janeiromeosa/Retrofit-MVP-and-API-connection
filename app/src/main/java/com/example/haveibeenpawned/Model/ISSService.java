package com.example.haveibeenpawned.Model;

import com.example.haveibeenpawned.Model.HaveIBeenPawnedRepo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.example.haveibeenpawned.Model.Constants.END_POINT;

public interface ISSService {

       @GET(END_POINT)
       Call<List<HaveIBeenPawnedRepo>> getRepos(@Query("domain") String domain);
}
