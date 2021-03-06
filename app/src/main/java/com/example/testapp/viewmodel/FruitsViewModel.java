package com.example.testapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.testapp.entity.Fruits;
import com.example.testapp.repository.FruitsRepository;

import java.util.List;

public class FruitsViewModel extends AndroidViewModel
{
    private FruitsRepository mRepository;

    private final LiveData<List<Fruits>> mAllFruits;

    public FruitsViewModel(@NonNull Application application)
    {
        super(application);
        mRepository = new FruitsRepository(application);
        mAllFruits = mRepository.getAllFruits();
    }

    public LiveData<List<Fruits>> getAllFruits()
    {
        return mAllFruits;
    }

    public void insertAll(Fruits fruits)
    {
        mRepository.insert(fruits);
    }

    public void deleteAll(Fruits fruits)
    {
        mRepository.delete(fruits);
    }

    public void update(Fruits fruits)
    {
        mRepository.update(fruits);
    }
}
