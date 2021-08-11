package ua.od.atomspace.account;

import ua.od.atomspace.Person;

import java.util.Date;

public class BankAccountManager {
    private BankAccount[] accounts;

    public BankAccountManager() {
        this.accounts = new BankAccount[0];
    }

    public BankAccount addNewAccount(Person owner, double balance) {
        BankAccount newBankAccount = new BankAccount(balance, owner);
        BankAccount[] bufferAccounts = new BankAccount[this.accounts.length + 1];
        for (int i = 0; i < this.accounts.length; i++) {
            bufferAccounts[i] = accounts[i];
        }
        bufferAccounts[this.accounts.length] = newBankAccount;
        accounts = bufferAccounts;
        return newBankAccount;
    }

    public BankAccount findBankAccountById(String id) {
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i].getIdAccount().equals(id)) {
                return accounts[i];
            }
        }
        System.out.println("Аккаунт с id: " + id + " не удалось найти!");
        return null;
    }

    public boolean deleteBankAccountById(String id) {
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i].getIdAccount().equals(id)) {
                BankAccount[] bufferAccounts_2 = new BankAccount[this.accounts.length - 1];
                int j = 0;
                for (; j < i; j++) {
                    bufferAccounts_2[j] = accounts[j];
                }
                i++;
                for (; j < bufferAccounts_2.length; j++) {
                    bufferAccounts_2[j] = accounts[i];
                }
                accounts = bufferAccounts_2;
                System.out.println("Аккаунт с id: " + id + " был успешно удален!");
                return true;
            }
        }
        System.out.println("Аккаунт с id: " + id + " не удалось найти!");
        return false;
    }

    public boolean transferMoney(String id_1, String id_2, double amount) {
        if (findBankAccountById(id_1) == null) {
            return false;
        }
        if (findBankAccountById(id_2) == null) {
            return false;
        }
        if (amount <= 0) {
            System.out.println("Сумма перевода должна быть больше 0!");
            return false;
        }
        if (findBankAccountById(id_1).getAccountBalance() < amount) {
            System.out.println("На аккаунте с id: " + id_1 + " баланс меньше суммы перевода!");
            Date date = new Date();
            findBankAccountById(id_1).addNewTransaction("Неудачная попытка перевода на аккаунт с id: " + id_2 + " на сумму: " + amount + ". Дата и время: " + date.toString());
            return false;
        }
        findBankAccountById(id_1).setAccountBalance(findBankAccountById(id_1).getAccountBalance() - amount);
        findBankAccountById(id_2).setAccountBalance(findBankAccountById(id_2).getAccountBalance() + amount);
        findBankAccountById(id_1).addNewTransaction("Перевод на сумму: "+ amount+"$ -> "+id_2);
        findBankAccountById(id_2).addNewTransaction("Перевод с акканта id: " + id_1 + " на сумму: " + amount + "$!");
        System.out.println("Успешно переведено!");
        return true;
    }

    public boolean refillBankAccount(double amount, String id) {
        if (findBankAccountById(id) == null) {
            return false;
        }
        if (amount <= 0) {
            System.out.println("Сумма пополнения должна быть больше 0!");
            Date date = new Date();
            for (int i = 0; i < accounts.length; i++) {
                if (accounts[i].getIdAccount().equals(id)) {
                    accounts[i].addNewTransaction("Неудачная попытка пополнения счета " + accounts[i].getIdAccount() + ". Дата и время операции: " + date.toString());
                }
            }
            return false;
        }
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i].getIdAccount().equals(id)) {
                accounts[i].setAccountBalance(accounts[i].getAccountBalance() + amount);
                Date date = new Date();
                accounts[i].addNewTransaction("Пополнение счета " + accounts[i].getIdAccount() + " на сумму " + amount + "$. Дата и время операции: " + date.toString());
            }
        }
        System.out.println("Успешно пополнено!");
        return true;
    }

    public void printAccountsInfo() {
        for (int i = 0; i < accounts.length; i++) {
            accounts[i].bankAccountInfo();
        }
    }
}
