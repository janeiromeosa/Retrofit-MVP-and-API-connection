package com.example.haveibeenpawned.Model;

import java.util.List;

public interface DataObserver {
    public void onSuccess(List<HaveIBeenPawnedRepo> result);
    public void onFailure(String error);
}
