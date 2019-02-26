package com.example.haveibeenpawned.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.haveibeenpawned.Model.Constants;
import com.example.haveibeenpawned.Model.HaveIBeenPawnedRepo;
import com.example.haveibeenpawned.Model.ISSService;
import com.example.haveibeenpawned.Presenter.PresenterContract;
import com.example.haveibeenpawned.Presenter.PresenterData;
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

public class MainActivity extends AppCompatActivity implements PresenterContract.View {

    RemoteAdapter remoteAdapter = new RemoteAdapter();
    Button btnGetLocations;
    EditText etDomain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // Presenter homePresenter = (Presenter)(new HomePresenter((View)this));

        final PresenterData presenterData = new PresenterData(this);

        RecyclerView recyclerView = findViewById(R.id.rvData);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration divider = new DividerItemDecoration(this,linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(divider);
        recyclerView.setAdapter(remoteAdapter);

        etDomain = findViewById(R.id.etDomain);
        btnGetLocations = findViewById(R.id.btnGetResult);

        btnGetLocations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenterData.getDomainInfo(etDomain.getText().toString());
            }
        });
    }

    @Override
    public void showData(List<HaveIBeenPawnedRepo> result) {
        remoteAdapter.setData(result);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();

    }
}
