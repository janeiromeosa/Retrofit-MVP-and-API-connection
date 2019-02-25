package com.example.haveibeenpawned.Presenter;

import com.example.haveibeenpawned.Model.HaveIBeenPawnedDataClass;
import com.example.haveibeenpawned.Model.HaveIBeenPawnedRepo;

public interface PresenterContract {
    interface Presenter{
        public void getDomainInfo(String domain);
    }

    interface View {
        public void showData(HaveIBeenPawnedRepo result); //parameter changed from nothing to haveibeenpawneddataclass
        public void showError(String error);
    }
}
