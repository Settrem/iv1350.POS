package se.kth.iv1350.cashregister.model;

public interface RevenueObserver {
    /**
     * invoked when a sale is made
     * keeps track of how much money has been made throughout the program's runtime
     * @param money the total sum to be added
     */
    void newSale(int money);
}
