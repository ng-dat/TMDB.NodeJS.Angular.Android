package com.example.tmdbandroid.screen.main;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class HomeViewModelFactory implements ViewModelProvider.Factory {
    private Application mApplication;
    private Context context;


    public HomeViewModelFactory(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(HomeViewModel.class)) {
            return (T) new HomeViewModel(context);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");

    }
}