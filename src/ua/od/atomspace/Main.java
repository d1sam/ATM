package ua.od.atomspace;

import ua.od.atomspace.account.BankAccountManager;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int action;
        BankAccountManager manager = new BankAccountManager();
        while (true) {
            System.out.println("Какое действие Вы хотите сделать?\n" +
                    "1 - Добавить новый аккаунт. 2 - Просмотреть информацию обо всех аккаунтах. 3 - Посмотреть информацию об определенном аккаунте по id.\n" +
                    "4 - Удалить аккаунт по id. 5 - Пополнить счет аккаунта по id. 6 - Совершить перевод между счетами 2х аккаунтов.\n" +
                    "7 - Просмотреть историю транзакций аккаунта по id. 8 - Выйти.");
            action = scanner.nextInt();
            switch (action) {
                case 1:
                    System.out.println("Введите имя владельца аккаунта: ");
                    String name = scanner.next();
                    System.out.println("Введите фамилию владельца аккаунта: ");
                    String surName = scanner.next();
                    Person owner = new Person(name, surName);
                    System.out.println("Введите начальную сумму на аккаунте: ");
                    double sum = scanner.nextDouble();
                    manager.addNewAccount(owner, sum);
                    continue;
                case 2:
                    manager.printAccountsInfo();
                    continue;
                case 3:
                    System.out.println("Какой id Вас интересует?");
                    String idInfo = scanner.next();
                    manager.findBankAccountById(idInfo).bankAccountInfo();
                    continue;
                case 4:
                    System.out.println("Какой id Вас интересует?");
                    String idDelete = scanner.next();
                    manager.deleteBankAccountById(idDelete);
                    continue;
                case 5:
                    System.out.println("Какой id Вас интересует?");
                    String idRefill = scanner.next();
                    System.out.println("На какую сумму Вы хотите пополнить счет аккаунта?");
                    Double sumRefill = scanner.nextDouble();
                    manager.refillBankAccount(sumRefill, idRefill);
                    continue;
                case 6:
                    System.out.println("Введите id аккаунта, со счета которого хотите совершить перевод: ");
                    String id_1 = scanner.next();
                    System.out.println("Введите id аккаунта, на счет которого хотите соверить перевод: ");
                    String id_2 = scanner.next();
                    System.out.println("Введите сумму перевода: ");
                    Double amount = scanner.nextDouble();
                    manager.transferMoney(id_1, id_2, amount);
                    continue;
                case 7:
                    System.out.println("Какой id Вас интересует?");
                    String idTransferHistory = scanner.next();
                    manager.findBankAccountById(idTransferHistory).printTransactionsHistory();
                    continue;
                case 8:
                    System.out.println("Завершено!");
                    break;
                default:
                    System.out.println("К сожалению, такой опции не сущуествует!");
                    continue;
            }
        }
    }
}