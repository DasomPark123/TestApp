package com.example.testapp.VO;

public class DataItem
{
    boolean isCheck;
    String name;
    String price;

    public DataItem(boolean isCheck, String name, String price)
    {
        this.isCheck = isCheck;
        this.name = name;
        this.price = price;
    }

    public DataItem(String name, String price)
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


