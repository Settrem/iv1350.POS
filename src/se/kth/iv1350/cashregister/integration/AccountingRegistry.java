package se.kth.iv1350.cashregister.integration;
import se.kth.iv1350.cashregister.model.Sale;
import java.util.ArrayList;

/**
 * Used to register Sales in the accounting system
 */
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
