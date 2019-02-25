package com.example.haveibeenpawned;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.haveibeenpawned.R;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etDomain;
    Button btnGetResult;
    RemoteAdapter remoteAdapter = new RemoteAdapter();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etDomain = findViewById(R.id.etDomain);
        btnGetResult = findViewById(R.id.btnGetResult);

        RecyclerView recyclerView = findViewById(R.id.rvData);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration divider = new DividerItemDecoration(this,linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(divider);
        recyclerView.setAdapter(remoteAdapter);

        btnGetResult.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnGetResult:
                searchClicked();
                break;
        }
    }

    public void searchClicked(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder().
                addInterceptor(new HttpLoggingInterceptor().
                        setLevel(HttpLoggingInterceptor.Level.BODY)).
                connectTimeout(20, TimeUnit.SECONDS).build();

        Retrofit.Builder retrofitBuilder = new Retrofit.Builder().
                baseUrl(Constants.BASE_URL).client(okHttpClient).
                addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = retrofitBuilder.build();

        ISSService issService = retrofit.create(ISSService.class);

        issService.getRepos(etDomain.getText().toString()).enqueue(new Callback<List<HaveIBeenPawnedRepo>>() {
            @Override
            public void onResponse(Call<List<HaveIBeenPawnedRepo>> call, Response<List<HaveIBeenPawnedRepo>> response) {
                if(response.isSuccessful()){
                    remoteAdapter.setData(response.body());
                }else{
                    Toast.makeText(MainActivity.this,"Search not successful", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<HaveIBeenPawnedRepo>> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Connection to Network failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
