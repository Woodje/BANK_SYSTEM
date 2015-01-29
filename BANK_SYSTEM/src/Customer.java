/**
 * Created by Woodje on 29-01-2015.
 */
public class Customer {

    private int id, accountId;

    private String name, address;

    // Customers constructor.
    public Customer (int id) {

        this.id = id;

    }

    public int getId() {

        return id;

    }

    public void setAccountId(int accountId) {

        this.accountId = accountId;

    }

    public int getAccountId() {

        return accountId;

    }

    public void setName(String name) {

        this.name = name;

    }

    public String getName() {

        return name;

    }

    public void setAddress(String address) {

        this.address = address;

    }

    public String getAddress() {

        return address;

    }
}
