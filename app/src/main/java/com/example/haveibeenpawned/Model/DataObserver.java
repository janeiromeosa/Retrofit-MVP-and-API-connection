package com.example.haveibeenpawned.Model;

public interface DataObserver {
    public void onSuccess(HaveIBeenPawnedRepo result);
    public void onFailure(String error);
}
