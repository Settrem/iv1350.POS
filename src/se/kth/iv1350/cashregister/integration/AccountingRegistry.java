package se.kth.iv1350.cashregister.integration;
import se.kth.iv1350.cashregister.model.Sale;
import java.util.ArrayList;


public class AccountingRegistry {
    private ArrayList<Sale> sales;
    public AccountingRegistry() {
        this.sales = new ArrayList<Sale>();
    }

    public int accountSale(Sale sale) {
        sales.add(sale);
        return 0;
    }
}
