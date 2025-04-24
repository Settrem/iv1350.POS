package se.kth.iv1350.cashregister.DTOs;

public class ItemDTO {

    public int ItemID;

    private String description;
    private int price;
    private double vAT;

    

    public double getVAT(){
        return vAT;
    }

    public String getDescription(){
        return description;
    }

    public int getPrice(){
        return price;
    }



}
