package com.example.haveibeenpawned.Presenter;

import com.example.haveibeenpawned.Model.DataObserver;
import com.example.haveibeenpawned.Model.DataSource;
import com.example.haveibeenpawned.Model.HaveIBeenPawnedRepo;
import com.example.haveibeenpawned.Model.RemoteDataSource;

import java.util.List;

public class PresenterData implements PresenterContract.Presenter, DataObserver {

    private final PresenterContract.View view;

    public PresenterData(PresenterContract.View view) { //view is not supposed to be null, error located.
        this.view = view;
    }


    @Override
    public void onSuccess(List<HaveIBeenPawnedRepo> result) {
        this.view.showData(result);
    }

    @Override
    public void onFailure(String error) {
        this.view.showError(error);
    }


    @Override
    public void getDomainInfo(String domain) {
        final DataSource dataSource = new RemoteDataSource(this);
        if (domain.isEmpty()) {
            view.showError("Enter domain from list");
            return;
        }else {
            dataSource.getDataForPawnedSite(domain);
        }

    }
}
