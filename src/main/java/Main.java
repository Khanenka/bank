import acc.Account;
import acc.Bank;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Scanner;

import static acc.Account.scanner;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, SQLException {
        //create users table
//        User.createTableUsers();


        // create bank
//        Bank.createTableBank();

//        Bank.insertTable();

        //create accounts table
//        Account.createTableAccounts();
//
//
//        Account.insertAccountsTable();


//        Transaction.createTableTransaction();
//        Transaction.insertTransactionTable();


        BigDecimal bigDecimal1 = new BigDecimal(100);
        BigDecimal bigDecimal2 = new BigDecimal(200);
        System.out.println(bigDecimal1.compareTo(bigDecimal2));

//        Client bankService = new Client();
//
//
//
//
//
////
////           bankService.searchAccByNumberAndPinCard();
//
//
//
//
//
//
//
//        bankService.firstService();
//        System.out.println(bankService.getIdAccounts());


    }



}
