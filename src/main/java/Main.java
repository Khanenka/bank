import acc.Account;
import acc.Bank;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import static acc.Account.scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        //create users table
//        User.createTableUsers();


        // create bank
//        Bank.createTableBank();

//        Bank.insertTable();

        //create accounts table
//        Account.createTableAccounts();


//        Account.insertAccountsTable();


//        Transaction.createTableTransaction();
//        Transaction.insertTransactionTable();


        BankService bankService = new BankService();
        System.out.println(bankService.searchAccByNumberCard());

//
//        System.out.println("Введите pin: ");
//
//        System.out.println("инфо по карте");
//
//
//        int pin = scanner.nextInt();
//        System.out.println("pin="+pin);
//        System.out.println("Выбирите транзакцию :");
//        System.out.println("Введите сумму :");
//        System.out.println("Печатать чек");
//        System.out.println("Вы хотите продолжить");





    }



}
