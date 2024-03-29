import acc.Account;

import java.math.BigDecimal;
import java.sql.*;
import java.util.Scanner;

import static acc.Account.*;

public class BankService {
    Account account = new Account();
    ResultSet resultSet = null;
    Scanner scanner = new Scanner(System.in);
    int accountNumber;
    int pin;

    public BankService() {
    }


    public void firstService() throws SQLException {

        System.out.println();



        System.out.println("Hello CLEVER-BANK");
        System.out.println("-----------------");


        boolean flag = true;
        while (flag) {
            accountNumber = account.inputAccNumber();

            pin = account.inputPinCode();
            Account acc = account.accInfoByNumberAndPin(accountNumber, pin);
            int accNumber = acc.getAccountNumber();
            int pin = acc.getPinCode();
            BigDecimal balance = acc.getBalance();
            String banikId = acc.getBankName();
            System.out.println(account.accountsBalance(accNumber, pin));
            if (accNumber != 0 && pin != 0) {
                int numberOperation = numberOperation();

                if (numberOperation == 1) {
                    System.out.println(acc.accountsBalance(accNumber, pin));
                    flag = true;
                } else if (numberOperation == 2) {
                    System.out.println("Введите сумму пополнения счета");
                    BigDecimal sumByDebit = scanner.nextBigDecimal();
                    debit(sumByDebit);
                    System.out.println("Операция проведена успешно");
                } else if (numberOperation == 3) {
                    System.out.println("Введите сумму снятия со счета");
                    BigDecimal sumByDebit = scanner.nextBigDecimal();
                    int equal = sumByDebit.compareTo(acc.accountsBalance(accNumber, pin));
                    BigDecimal subtract = acc.accountsBalance(accNumber, pin).subtract(sumByDebit);
                    System.out.println(subtract + " " + acc.accountsBalance(accNumber, pin));
                    if (equal == -1 || equal == 0) {
                        creditOperation(subtract, getIdAccounts());
                        System.out.println("Операция проведена успешно");
                    } else {
                        System.out.println("Недостаточно средств");
                    }
                }
                if (numberOperation == 5) {
                    flag = false;
                }
            } else {
                System.out.println("Неправильный номер карты или пароль");
            }
        }
    }

    private void creditOperation(BigDecimal balanceAfterCredit, Integer getIdAccount) throws SQLException {
        String sqlAccDebitByAccAndPin = "UPDATE accounts SET Balance = " + balanceAfterCredit + " WHERE IdAccount = " + getIdAccount;
        PreparedStatement preparedStatement = conn.prepareStatement(sqlAccDebitByAccAndPin);
        preparedStatement.executeUpdate();
    }

    public Integer getIdAccounts() {
        Integer idAcc = null;
        String getIdAccount = "SELECT IdAccount FROM accounts where AccountNumber = " + accountNumber + " AND PinCode = " + pin;
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            PreparedStatement preparedStatement = conn.prepareStatement(getIdAccount);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                idAcc = resultSet.getInt("IdAccount");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return idAcc;
    }

    private void debit(BigDecimal sumByDebit) throws SQLException {
        BigDecimal balance = account.accountsBalance(accountNumber, pin);
        System.out.println(balance);
        BigDecimal newBalance = balance.add(sumByDebit);
        System.out.println(newBalance);
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            Integer getIdAccount = getIdAccounts();
            String sqlAccDebitByAccAndPin = "UPDATE accounts SET Balance = " + newBalance + " WHERE IdAccount = " + getIdAccount;
            PreparedStatement preparedStatement = conn.prepareStatement(sqlAccDebitByAccAndPin);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public Boolean searchAccByNumberAndPinCard() {
        try {
            String sqlAccByPin = "SELECT * FROM accounts where AccountNumber = " + accountNumber + " AND PinCode = " + pin;
            PreparedStatement preparedStatement = conn.prepareStatement(sqlAccByPin);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                account.setAccountNumber(resultSet.getInt("AccountNumber"));
                account.setPinCode(resultSet.getInt("PinCode"));
                if (account.getAccountNumber() != null && account.getPinCode() != null) {
                    return true;
                }
                return false;
            }
        } catch (Exception ex) {
            System.out.println("Connection failed...");
            System.out.println(ex);
        }
        return false;
    }


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
