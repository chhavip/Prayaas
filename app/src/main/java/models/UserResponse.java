package models;

/**
 * Created by shikharkhetan on 7/1/15.
 */
public class UserResponse {

    private String users_uid;
    private String transaction_type;
    private String amount;
    private String wallet;
    private String transaction_date;



    public String getUsers_uid() {
        return users_uid;
    }

    public void setUsers_uid(String users_uid) {
        this.users_uid = users_uid;
    }

    public String getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(String transaction_type) {
        this.transaction_type = transaction_type;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getWallet() {
        return wallet;
    }

    public void setWallet(String wallet) {
        this.wallet = wallet;
    }

    public String getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(String transaction_date) {
        this.transaction_date = transaction_date;
    }

    public UserResponse() {


    }
}
