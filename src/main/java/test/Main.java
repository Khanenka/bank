package test;

import test.crud.CRUDAccount;
import test.crud.CRUDBank;
import test.services.AccountService;
import test.services.BankService;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.sql.Date.valueOf;

public class Main {



    SimpleDateFormat ft =
            new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");

    public static void main(String[] args) throws SQLException {
        Date date = new Date();
Main main=new Main();
        System.out.println(main.ft.format(date));


//        account.setNumberAccount(1111);
//        account.setPasswordAccount(1111);
//        account.setBalance(BigDecimal.valueOf(100));
//        account.setCreateAccounts(valueOf("2013-09-04"));
//        account.setTimeOfAction(valueOf("2033-09-04"));
//        account.setBankName("Clever");

//        CRUDBank crudBank=new CRUDBank();
//        crudBank.createTableBank();
//        CRUDAccount crudAccount=new CRUDAccount();
//        crudAccount.createTableAccounts();


    }
}
