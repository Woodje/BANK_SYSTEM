import java.util.ArrayList;

/**
 * Created by Woodje on 29-01-2015.
 */
public class Account {

    private int id, customerId;

    private double balance;

    private ArrayList<Transaction> transactionList;

    // Accounts constructor.
    public Account(int id) {

        this.id = id;

        transactionList = new ArrayList<Transaction>();

    }

    public int getId() {

        return id;

    }

    public void setCustomerId(int customerId) {

        this.customerId = customerId;

    }

    public int getCustomerId() {

        return customerId;

    }

    public void setBalance(double balance) {

        this.balance = balance;

    }

    public double getBalance() {

        return balance;

    }

    public void setTransactionList(Transaction transaction) {

        transactionList.add(transaction);

    }

    public ArrayList<Transaction> getTransactionList() {

        return transactionList;

    }
}
