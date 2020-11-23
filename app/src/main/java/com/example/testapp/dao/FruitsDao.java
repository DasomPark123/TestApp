package com.example.testapp.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.testapp.VO.Fruits;

import java.util.List;

@Dao
public interface FruitsDao
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(String name, String price);

    @Update
    void update(Fruits fruits);

    @Delete
    void delete();

    @Query("SELECT * FROM fruits_table ORDER BY name ASC")
    List<Fruits> getAllFruitsByAsc();

    @Query("DELETE FROM fruits_table")
    void deleteAll();

}
