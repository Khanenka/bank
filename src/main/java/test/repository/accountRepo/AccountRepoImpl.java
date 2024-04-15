package test.repository.accountRepo;

import test.Account;
import test.utility.DBConnection;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import static test.utility.DBConnection.conn;


public class AccountRepoImpl implements AccountRepo {

    Account account = new Account();
    BigDecimal balance = account.getBalance();
    Scanner scanner = new Scanner(System.in);

    @Override
    public Account accInfoByNumberAndPin(Integer accountNumber, Integer pinCode) throws SQLException {

        Integer accountId = account.getAccountId();
        Integer numberAccount = accountNumber;
        Integer passwordAccount = pinCode;
        BigDecimal balance = account.getBalance();
        Date createAccounts = account.getCreateAccounts();
        Date timeOfAction = account.getTimeOfAction();
        Integer bankId = account.getBankId();

        String sqlAccByPin = "SELECT * FROM account where numberAccount = " + accountNumber + " AND passwordAccount = " + pinCode;
        PreparedStatement preparedStatement = conn.prepareStatement(sqlAccByPin);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            accountId = resultSet.getInt("accountId");
            numberAccount = resultSet.getInt("numberAccount");
            passwordAccount = resultSet.getInt("passwordAccount");
            balance = resultSet.getBigDecimal("balance");
            createAccounts = resultSet.getDate("createAccounts");
            timeOfAction = resultSet.getDate("timeOfAction");
            bankId = resultSet.getInt("bankId");
            System.out.println(bankId);
        }
        account.setAccountId(accountId);
        account.setNumberAccount(numberAccount);
        account.setPasswordAccount(passwordAccount);
        account.setBalance(balance);
        account.setCreateAccounts(createAccounts);
        account.setTimeOfAction(timeOfAction);
        account.setBankId(bankId);
        return account;
    }

    @Override
    public BigDecimal accountsBalance(Integer accountNumber, Integer pinCode) throws SQLException {
        String sqlBalanceByPinAndAcc = "SELECT balance FROM account where numberAccount=" + accountNumber + " AND passwordAccount = " + pinCode;
        PreparedStatement preparedStatement = DBConnection.test().prepareStatement(sqlBalanceByPinAndAcc);
        ResultSet resultSet = preparedStatement.executeQuery();
        System.out.println(accountNumber);
        while (resultSet.next()) {
            balance = resultSet.getBigDecimal("Balance");
        }
        return balance;
    }

    @Override
    public BigDecimal accountsBalanceByAccountNumber(Integer accountNumber) throws SQLException {
        String sqlBalanceByPinAndAcc = "SELECT balance FROM accounts where AccountNumber=" + accountNumber;
        PreparedStatement preparedStatement = conn.prepareStatement(sqlBalanceByPinAndAcc);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            balance = resultSet.getBigDecimal("Balance");
        }
        return balance;
    }

    @Override
    public ArrayList<Integer> inputAccAndPin() {
        ArrayList<Integer> numberAndPin = new ArrayList<>();
        Integer number;
        Integer pin;


        do {
            System.out.println("Введите номер карты");
            while (!scanner.hasNextInt()) {
                System.out.println("That's not a number!");
                scanner.next(); // this is important!
            }
            number = scanner.nextInt();
        } while (number <= 0);
        System.out.println(number);

        do {
            System.out.println("Введите пароль карты");
            while (!scanner.hasNextInt()) {
                System.out.println("That's not a number!");
                scanner.next(); // this is important!
            }
            pin = scanner.nextInt();
        } while (pin <= 0);
        System.out.println(pin);
        numberAndPin.add(number);
        numberAndPin.add(pin);
        System.out.println(number);
        return numberAndPin;
    }


}
