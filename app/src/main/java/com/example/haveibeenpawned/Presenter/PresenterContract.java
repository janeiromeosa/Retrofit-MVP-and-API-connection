package com.example.haveibeenpawned.Presenter;

import com.example.haveibeenpawned.Model.HaveIBeenPawnedDataClass;
import com.example.haveibeenpawned.Model.HaveIBeenPawnedRepo;

import java.util.List;

public interface PresenterContract {

    interface View {
        public void showData(List<HaveIBeenPawnedRepo> result); //parameter changed from nothing to haveibeenpawneddataclass
        public void showError(String error);
    }

    interface Presenter{
        public void getDomainInfo(String domain);
    }
}
