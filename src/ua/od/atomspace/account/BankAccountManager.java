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
            if (accounts[i].getAccountId().equals(id)) {
                return accounts[i];
            }
        }
        System.out.println("Аккаунт с id: " + id + " не удалось найти!");
        return null;
    }

    public boolean deleteBankAccountById(String id) {
    for (int i = 0; i < accounts.length; i++) {
            if (accounts[i].getAccountId().equals(id)) {
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

    public boolean transferMoney(String id1, String id2, double amount) {
        BankAccount bankAccount1 = findBankAccountById(id1) ==null? null : findBankAccountById(id1);
        BankAccount bankAccount2 = findBankAccountById(id2) ==null? null : findBankAccountById(id2);
        if (bankAccount1 == null) {
            return false;
        }
        if (bankAccount2 == null) {
            return false;
        }
        if (amount <= 0) {
            System.out.println("Сумма перевода должна быть больше 0!");
            return false;
        }
        if (bankAccount1.getAccountBalance() < amount) {
            System.out.println("На аккаунте с id: " + id1 + " баланс меньше суммы перевода!");
            Date date = new Date();
            bankAccount1.addNewTransaction("Неудачная попытка перевода на аккаунт с id: " + id2 + " на сумму: " + amount + ". Дата и время: " + date.toString());
            return false;
        }
        bankAccount1.setAccountBalance(bankAccount1.getAccountBalance() - amount);
        bankAccount2.setAccountBalance(bankAccount2.getAccountBalance() + amount);
        bankAccount1.addNewTransaction("Перевод на сумму: " + amount + "$ -> " + id2);
        bankAccount2.addNewTransaction("Перевод с акканта id: " + id1 + " на сумму: " + amount + "$!");
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
            findBankAccountById(id).addNewTransaction("Неудачная попытка пополнения счета " + findBankAccountById(id).getAccountId() + ". Дата и время операции: " + date.toString());
            return false;
        }
        findBankAccountById(id).setAccountBalance(findBankAccountById(id).getAccountBalance() + amount);
        Date date = new Date();
        findBankAccountById(id).addNewTransaction("Пополнение счета " + findBankAccountById(id).getAccountId() + " на сумму " + amount + "$. Дата и время операции: " + date.toString());
        System.out.println("Успешно пополнено!");
        return true;
    }

    public void printAccountsInfo() {
        for (int i = 0; i < accounts.length; i++) {
            accounts[i].bankAccountInfo();
        }
    }
}
