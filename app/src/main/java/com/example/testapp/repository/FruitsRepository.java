package com.example.testapp.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.testapp.VO.Fruits;
import com.example.testapp.dao.FruitsDao;
import com.example.testapp.database.FruitsDatabase;

import java.util.List;

public class FruitsRepository
{
    private FruitsDao mFruitDao;
    private LiveData<List<Fruits>> mAllFruits;

    public FruitsRepository(Application application)
    {
        FruitsDatabase db = FruitsDatabase.getFruitsDatabase(application);
        mFruitDao = db.fruitsDao();
        mAllFruits = mFruitDao.getAllFruitsByAsc();
    }

    public LiveData<List<Fruits>> getAllFruits()
    {
        return mAllFruits;
    }

    public void insertAll(final Fruits fruits)
    {
        FruitsDatabase.databaseWriteExecutor.execute(new Runnable()
        {
            @Override
            public void run()
            {
                mFruitDao.insert(fruits);
            }
        });
    }

    public void update(final Fruits fruits)
    {
        FruitsDatabase.databaseWriteExecutor.execute(new Runnable()
        {
            @Override
            public void run()
            {
                mFruitDao.update(fruits);
            }
        });
    }

    public void deleteAll(final Fruits fruits)
    {
        FruitsDatabase.databaseWriteExecutor.execute(new Runnable()
        {
            @Override
            public void run()
            {
                mFruitDao.deleteAll(fruits);
            }
        });
    }
}
