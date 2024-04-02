import acc.Account;

import java.io.*;
import java.math.BigDecimal;
import java.net.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

import static acc.Account.*;


// Client class
class Client {


    static Account account=new Account();
    static Integer accountNumber=account.getAccountNumber();
    static Integer pin=account.getPinCode();

    // driver code
    public static void main(String[] args) {

       Client client=new Client();


        ArrayList<Integer> arrayList = new ArrayList<>();
        // establish a connection by providing host and port
        // number
        try (Socket socket = new Socket("localhost", 1234)) {

            // writing to server
            PrintWriter out = new PrintWriter(
                    socket.getOutputStream(), true);

            // reading from server
            BufferedReader in
                    = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));

            // object of scanner class
            Scanner sc = new Scanner(System.in);

            while (true) {

                arrayList = account.inputAccAndPin();
                for (int i = 0; i < arrayList.size(); i++) {
                    accountNumber = arrayList.get(0);
                    pin = arrayList.get(1);
                }
                account.setAccountNumber(accountNumber);
                account.setPinCode(pin);
//                System.out.println(account.getPinCode());
//                System.out.println(account.getAccountNumber());


                int numberOperation = client.numberOperation();
                if (numberOperation == 1) {
                    System.out.println(account.accountsBalance(accountNumber, pin));

                } else if (numberOperation == 2) {
                    System.out.println("Введите сумму пополнения счета");
                    BigDecimal sumByDebit = scanner.nextBigDecimal();
                    client.debit(sumByDebit);
                    System.out.println("Операция проведена успешно");
                } else if (numberOperation == 3) {
                    System.out.println("Введите сумму снятия со счета");
                    BigDecimal sumByDebit = scanner.nextBigDecimal();
                    int equal = sumByDebit.compareTo(account.accountsBalance(accountNumber, pin));
                    BigDecimal subtract = account.accountsBalance(accountNumber, pin).subtract(sumByDebit);

                    System.out.println(subtract + " " + account.accountsBalance(accountNumber, pin));
                    if (equal == -1 || equal == 0) {
                        System.out.println(accountNumber);
                        client.creditOperation(subtract, client.getIdAccounts(accountNumber, pin));
                        System.out.println("Операция проведена успешно");
                    } else {
                        System.out.println("Недостаточно средств");
                    }
                }
                if (numberOperation == 5) {

                }


                out.println(accountNumber);
                out.println(pin);
                out.flush();


                // displaying server reply
                System.out.println(
                        accountNumber);
                System.out.println(pin);
            }

            // closing the scanner object

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void creditOperation(BigDecimal balanceAfterCredit, Integer getIdAccount) throws SQLException {
        String sqlAccDebitByAccAndPin = "UPDATE accounts SET Balance = " + balanceAfterCredit + " WHERE IdAccount = " + getIdAccount;
        PreparedStatement preparedStatement = conn.prepareStatement(sqlAccDebitByAccAndPin);
        System.out.println(sqlAccDebitByAccAndPin);
        preparedStatement.executeUpdate();

    }
    public Integer getIdAccounts(Integer accNumber,Integer pin) {
        Integer idAcc = null;
        String getIdAccount = "SELECT IdAccount FROM accounts where AccountNumber = " + accNumber + " AND PinCode = " + pin;
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
    public void debit(BigDecimal sumByDebit) throws SQLException {
        BigDecimal balance = account.accountsBalance(accountNumber, pin);
        BigDecimal newBalance = balance.add(sumByDebit);
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            Integer getIdAccount = getIdAccounts(accountNumber,pin);
            String sqlAccDebitByAccAndPin = "UPDATE accounts SET Balance = " + newBalance + " WHERE IdAccount = " + getIdAccounts(accountNumber,pin);
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
                if (accountNumber != null && pin != null) {
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