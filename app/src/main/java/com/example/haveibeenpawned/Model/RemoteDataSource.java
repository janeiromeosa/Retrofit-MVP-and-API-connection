package com.example.haveibeenpawned.Model;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteDataSource implements DataSource {

    private DataObserver listener;

    public RemoteDataSource(DataObserver listener) {
        this.listener = listener;
    }

    @Override
    public void getDataForPawnedSite(String domain) {

        OkHttpClient okHttpClient = new OkHttpClient.Builder().
                addInterceptor(new HttpLoggingInterceptor().
                        setLevel(HttpLoggingInterceptor.Level.BODY)).
                connectTimeout(20, TimeUnit.SECONDS).build();

        Retrofit.Builder retrofitBuilder = new Retrofit.Builder().
                baseUrl(Constants.BASE_URL).client(okHttpClient).
                addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = retrofitBuilder.build();

        ISSService issService = retrofit.create(ISSService.class);

        issService.getRepos(domain).enqueue(new Callback<List<HaveIBeenPawnedRepo>>() {
            @Override
            public void onResponse(Call<List<HaveIBeenPawnedRepo>> call, Response<List<HaveIBeenPawnedRepo>> response) {
                List<HaveIBeenPawnedRepo> haveIBeenPawnedRepo = response.body();
                listener.onSuccess((List<HaveIBeenPawnedRepo>) haveIBeenPawnedRepo);
            }

            @Override
            public void onFailure(Call<List<HaveIBeenPawnedRepo>> call, Throwable throwable) {
                RemoteDataSource.this.listener.onFailure("An error occured");
            }
        });

        }
}
