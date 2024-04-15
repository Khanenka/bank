package test.services;





import lombok.*;
import test.repository.accountRepo.AccountRepo;
import test.repository.accountRepo.AccountRepoImpl;
import test.utility.DBConnection;

import java.math.BigDecimal;
import java.sql.*;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static test.utility.DBConnection.conn;

@Setter
@Getter


public class AccountService {

   public static Scanner scanner=new Scanner(System.in);

public static AccountRepo accountRepo=new AccountRepoImpl();

    private static final double INTEREST_RATE = 0.01;
    private static final int INTERVAL_SECONDS = 5;

    public static void main(String[] args) throws SQLException {
        Connection connection= DBConnection.test();
        AccountService accountService=new AccountService();
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        scheduler.scheduleAtFixedRate( () -> {
            long old = System.currentTimeMillis();
            try {
//                BigDecimal balance = accountRepo.accountsBalance(1234,1111);
//
//                BigDecimal test=balance.multiply(BigDecimal.valueOf(0.01));
//
//                String sqlAccDebitByAccAndPin = "UPDATE account SET balance = " + test.add(balance) + " WHERE accountId = " + 1;
//                PreparedStatement preparedStatement = connection.prepareStatement(sqlAccDebitByAccAndPin);
//
//                preparedStatement.executeUpdate();
                accountService.test();


            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


            System.out.println("Checking and applying interest...");
            long newTime = System.currentTimeMillis();
            System.out.println((newTime-old)/100);
            try {
                accountService.ex();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }, 0, INTERVAL_SECONDS, TimeUnit.SECONDS);



    }
    public Integer ex() throws SQLException {
        Integer integ=null;
        String sqlAccByPin = "SELECT count(*) FROM account";
        PreparedStatement preparedStatement = conn.prepareStatement(sqlAccByPin);
        ResultSet resultSet = preparedStatement.executeQuery();
        System.out.println(resultSet);
        while (resultSet.next()) {

             integ = resultSet.getInt("count(*)");


        }
       return  integ;
    }


    public void test() throws SQLException {
        BigDecimal balance = null;
        Integer s = ex();

            String sqlAccByPin = "SELECT balance FROM account";
            PreparedStatement preparedStatement = conn.prepareStatement(sqlAccByPin);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                balance = resultSet.getBigDecimal("balance");


            }
            BigDecimal test = balance.multiply(BigDecimal.valueOf(0.01));
            String sqlAccDebitByAccAndPin = "UPDATE account SET balance = " + test.add(balance);
            preparedStatement = conn.prepareStatement(sqlAccDebitByAccAndPin);
            System.out.println(sqlAccDebitByAccAndPin);
            preparedStatement.executeUpdate();


    }









//    public Boolean searchAccByNumberAndPinCard() {
//        try {
//            String sqlAccByPin = "SELECT * FROM accounts where AccountNumber = " + accountNumber + " AND PinCode = " + pin;
//            PreparedStatement preparedStatement = conn.prepareStatement(sqlAccByPin);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                account.setAccountNumber(resultSet.getInt("AccountNumber"));
//                account.setPinCode(resultSet.getInt("PinCode"));
//                if (accountNumber != null && pin != null) {
//                    return true;
//                }
//                return false;
//            }
//        } catch (Exception ex) {
//            System.out.println("Connection failed...");
//            System.out.println(ex);
//        }
//        return false;
//    }

    public Integer numberOperation() {
        System.out.println("Выберете номер операции:");
        System.out.println("1) Просмотр баланса");
        System.out.println("2) Пополнение счета");
        System.out.println("3) Снятие со счета");
        System.out.println("4) Перевод средств");
        System.out.println("5) Выход");
        int numberOperation = scanner.nextInt();
        return numberOperation;
    }


}
