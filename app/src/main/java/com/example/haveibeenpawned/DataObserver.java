package com.example.haveibeenpawned;

import com.example.haveibeenpawned.HaveIBeenPawnedDataClass;

public interface DataObserver {
    public void onSuccess(HaveIBeenPawnedDataClass result);
    public void onFailure(String error);
}
