package se.kth.iv1350.cashregister.view;

import se.kth.iv1350.cashregister.model.RevenueObserver;

public class TotalRevenueView implements RevenueObserver{
    private int totalOfSales;
    private static final double ÖRE_TO_SEK = 0.01;

    public TotalRevenueView() {
        this.totalOfSales = 0;
    }

    @Override
    public void updateRevenue(int amount){
        this.totalOfSales += amount;
    }

    public void printRevenue(){
        System.out.println("Total revenue: " + totalOfSales * ÖRE_TO_SEK);
    }
}