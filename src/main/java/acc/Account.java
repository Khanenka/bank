package acc;

import impl.AccountRepo;
import impl.AccountRepoImpl;
import lombok.*;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    private int idAccount;
    private Integer accountNumber;
    private Integer pinCode;
    private BigDecimal balance;
    private String bankName;
//   private Currency currency;

    public static String url = "jdbc:mysql://localhost/bank?serverTimezone=Europe/Moscow&useSSL=false";
    public static String username = "leonid";
    public static String password = "1234";
    public static Scanner scanner = new Scanner(System.in);
    public static ResultSet resultSet = null;
    public static Object x;
    public static Connection conn;

    private static String sqlCreateTableAccounts = "CREATE TABLE accounts (IdAccount INT PRIMARY KEY AUTO_INCREMENT, AccountNumber INT,PinCode INT, Balance Decimal (4,2), BankId INT,FOREIGN KEY (BankId) REFERENCES Bank(IdBank))";
    private static String sqlInsertAccountsTable = "INSERT INTO accounts (AccountNumber, PinCode, Balance, BankId) Values (?,?,?,?)";

    static {
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            x = Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createTableAccounts() throws SQLException {
        Statement statement = conn.createStatement();
        // создание таблицы
        statement.executeUpdate(sqlCreateTableAccounts);
        System.out.println("Database has been created!");

    }


    public static void insertAccountsTable() throws SQLException {
        System.out.print("Input AccountNumber: ");
        int numberAccount = scanner.nextInt();
        System.out.print("Input pinCode: ");
        int pinCode = scanner.nextInt();
        System.out.print("Input balance: ");
        BigDecimal balance = scanner.nextBigDecimal();
        System.out.println("Input bankName: ");
        int bankId = scanner.nextInt();
        PreparedStatement preparedStatement = conn.prepareStatement(sqlInsertAccountsTable);
        preparedStatement.setInt(1, numberAccount);
        preparedStatement.setInt(2, pinCode);
        preparedStatement.setBigDecimal(3, balance);
        preparedStatement.setInt(4, bankId);
        int rows = preparedStatement.executeUpdate();
        System.out.printf("%d rows added", rows);
    }


    public Account accInfoByNumberAndPin(Integer accountNumber,Integer pinCode) throws SQLException {
        int acc = 0;
        int pin = 0;
        Account account=new Account();
        BigDecimal balance = null;
        int bankId = 0;

         String sqlAccByPin = "SELECT * FROM accounts where AccountNumber = " + accountNumber + " AND PinCode = " + pinCode;
        PreparedStatement preparedStatement = conn.prepareStatement(sqlAccByPin);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            acc = resultSet.getInt("AccountNumber");
            pin = resultSet.getInt("PinCode");
            balance = resultSet.getBigDecimal("Balance");
            bankId = resultSet.getInt("BankId");
        }
        account.setAccountNumber(acc);
        account.setPinCode(pin);
        account.setBalance(balance);
//            arrayList.setBankName(bankId);
        System.out.println(Arrays.asList(account));

        return account;
    }
    public BigDecimal accountsBalance(Integer accountNumber, Integer pinCode) throws SQLException {
        BigDecimal balance = null;
        if (balance == null) {
            balance = balance.ZERO;
              String sqlBalanceByPinAndAcc = "SELECT balance FROM accounts where AccountNumber=" + accountNumber + " AND PinCode = " + pinCode;
                PreparedStatement preparedStatement = conn.prepareStatement(sqlBalanceByPinAndAcc);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    balance = resultSet.getBigDecimal("Balance");
                }
        }
        return balance;
    }


    public Integer inputAccNumber(){
        System.out.println("Введите номер карты");
        accountNumber = scanner.nextInt();
        return accountNumber;
    }
    public Integer inputPinCode(){
        System.out.println("Введите пароль карты");
        pinCode = scanner.nextInt();
        return pinCode;
    }



}
