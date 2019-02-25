package com.example.haveibeenpawned;

import com.example.haveibeenpawned.HaveIBeenPawnedDataClass;

import java.util.List;

public interface ViewContract {
    public void showData(List<HaveIBeenPawnedDataClass> result);
    public void showError (String error);
}
