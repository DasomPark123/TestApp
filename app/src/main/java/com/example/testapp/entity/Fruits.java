package com.example.testapp.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "fruits_table")
public class Fruits
{
    @Ignore
    boolean isCheck;

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "name")
    String name;

    @NonNull
    @ColumnInfo(name = "price")
    String price;

    public Fruits(boolean isCheck, String name, String price)
    {
        this.isCheck = isCheck;
        this.name = name;
        this.price = price;
    }

    public Fruits(String name, String price)
    {
        isCheck = false;
        this.name = name;
        this.price = price;
    }

    public boolean isCheck()
    {
        return isCheck;
    }

    public void setCheck(boolean check)
    {
        isCheck = check;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPrice()
    {
        return price;
    }

    public void setPrice(String price)
    {
        this.price = price;
    }
}


