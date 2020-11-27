package com.example.testapp.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.testapp.entity.Fruits;
import com.example.testapp.dao.FruitsDao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Fruits.class}, version = 1, exportSchema = true)
public abstract class FruitsDatabase extends RoomDatabase
{
    public abstract FruitsDao fruitsDao();

    private static FruitsDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor
             = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    public static FruitsDatabase getFruitsDatabase(Context context)
    {
        if(INSTANCE == null)
        {
            synchronized(FruitsDatabase.class)
            {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        FruitsDatabase.class, "fruit_database")
                        .addCallback(mRoomDatabaseCallback)
                        .build();
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback mRoomDatabaseCallback = new RoomDatabase.Callback()
    {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db)
        {
            super.onCreate(db);
            databaseWriteExecutor.execute(new Runnable()
            {
                @Override
                public void run()
                {
                    //Initializing
                    FruitsDao dao = INSTANCE.fruitsDao();
                    dao.deleteAll();

                    Fruits fruits = new Fruits("melon","9000");
                    dao.insert(fruits);
                    fruits = new Fruits("strawberry","15000");
                    dao.insert(fruits);
                }
            });
        }
    };
}
