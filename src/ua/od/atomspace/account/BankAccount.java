package ua.od.atomspace.account;

import ua.od.atomspace.Person;

import java.time.LocalDateTime;
import java.util.Arrays;

public class BankAccount {
    private final String id;
    private double accountBalance;
    private String[] transactionsHistory;
    private final Person owner;

    protected BankAccount(double accountBalance, Person owner) {
        id = getUniqueId();
        transactionsHistory = new String[5];
        Arrays.fill(transactionsHistory, "");
        this.owner = owner;
        this.accountBalance = accountBalance;
    }

    protected void addNewTransaction(String newTransaction) {
        for (int i = transactionsHistory.length - 1; i >0 ; i--) {
            transactionsHistory[i] = transactionsHistory[i-1];
        }
        transactionsHistory[0] = newTransaction;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    protected void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    private String getUniqueId() {
        LocalDateTime now = LocalDateTime.now();
        String idBuff = now.getYear() + "" + now.getDayOfYear() + "" + now.getDayOfMonth() + "" + now.getHour() + "" + now.getMinute() + "" + now.getSecond() + "" + now.getNano();
        return idBuff.substring(0, 20);
    }

    public String getIdAccount() {
        return id;
    }

    public void checkTransactionsHistory() {
        for (String item :
                transactionsHistory) {
            System.out.println(item);
        }
    }

    public Person getOwner() {
        return this.owner;
    }

    public void bankAccountInfo() {
        System.out.println("id: " + id + " | owner: " + owner + " | balance: " + accountBalance + "$");
    }
}