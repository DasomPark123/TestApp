package com.example.testapp.room;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.testapp.dao.TodoDao;

public abstract class TodoDatabase extends RoomDatabase
{
   /* Singleton */
   private static TodoDatabase INSTANCE;

   public abstract TodoDao todoDao();

   public static TodoDatabase getAppDatabase(Context context)
   {
      if(INSTANCE == null)
      {
         /* if we use .allowMainThreadQueries() instead of .build(), we can implement without using AsyncTask.
            But. it might cause exception.
          */
         INSTANCE = Room.databaseBuilder(context, TodoDatabase.class, "todo-db").build();
      }
      return INSTANCE;
   }

   public static void destroyInstance()
   {
      INSTANCE = null;
   }
}
