package com.example.testapp.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.testapp.entity.Fruits;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface FruitsDao
{
    @Query("SELECT * FROM fruits_table ORDER BY name ASC")
    LiveData<List<Fruits>> getAllFruitsByAsc();

    @Query("DELETE FROM fruits_table")
    void deleteAll();

    @Insert(entity = Fruits.class, onConflict = OnConflictStrategy.REPLACE)
    void insert(Fruits fruits);

    @Update(entity = Fruits.class)
    void update(Fruits fruits);

    @Delete
    void delete(Fruits fruits);
}
