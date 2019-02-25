package com.example.haveibeenpawned.Presenter;

import com.example.haveibeenpawned.Model.DataObserver;
import com.example.haveibeenpawned.Model.DataSource;
import com.example.haveibeenpawned.Model.HaveIBeenPawnedRepo;
import com.example.haveibeenpawned.Model.RemoteDataSource;

public class PresenterData implements PresenterContract.Presenter, DataObserver {

    private final PresenterContract.View view;

    public PresenterData(PresenterContract.View view) {
        this.view = view;
    }


    @Override
    public void onSuccess(HaveIBeenPawnedRepo result) {
        this.view.showData(result);
    }

    @Override
    public void onFailure(String error) {
        this.view.showError(error);
    }


    @Override
    public void getDomainInfo(String domain) {
        final DataSource dataSource = new RemoteDataSource(this) {
            @Override
            public void getDataForPawnedSite(String domain) {
                if (domain.isEmpty()){
                    view.showError("Enter domain from list");
                }



            }

        };

    }
}
