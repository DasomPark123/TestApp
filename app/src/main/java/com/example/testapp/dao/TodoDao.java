package com.example.testapp.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.testapp.VO.Todo;

import java.util.List;

@Dao
public interface TodoDao
{
    @Query("SELECT * FROM Todo")
    LiveData<List<Todo>> getAll();

    @Insert
    void insert(Todo todo);

    @Update
    void update(Todo todo);

    @Delete
    void delete(Todo todo);

    @Query("DELETE FROM Todo")
    void deleteAll();

}
