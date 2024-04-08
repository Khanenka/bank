package test;

import acc.Account;
import lombok.Synchronized;

import java.io.*;
import java.math.BigDecimal;
import java.net.*;
import java.sql.*;
import java.util.*;

import static acc.Account.*;


// test.Client class

public class Client {


    static Account account = new Account();
    static Integer accountNumber = account.getAccountNumber();
    static Integer pin = account.getPinCode();

    // driver code

    public static void clientService() {

        Client client = new Client();
        ArrayList<Integer> arrayList = new ArrayList<>();
        //создаем сокет
        try (Socket socket = new Socket("localhost", 1234)) {

            // пишем на сервер
            PrintWriter out = new PrintWriter(
                    socket.getOutputStream(), true);

            // читаем из сервера
            BufferedReader in
                    = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));

            // сканнер
            Scanner sc = new Scanner(System.in);
            String s = sc.nextLine();

            String word = in.readLine(); // ждём пока клиент что-нибудь
            // не напишет в консоль
            out.write(word + "\n"); // отправляем сообщение на сервер
            out.flush();
            String serverWord = in.readLine(); // ждём, что скажет сервер
            System.out.println(serverWord);

            //бесконечный цикл
//            while (true) {
//                //ввод номер и пин
//                arrayList = account.inputAccAndPin();
//                for (int i = 0; i < arrayList.size(); i++) {
//                    accountNumber = arrayList.get(0);
//                    pin = arrayList.get(1);
//                }
//                account.setAccountNumber(accountNumber);
//                account.setPinCode(pin);
//
//
//                //выбираем номер операции
//                int numberOperation = client.numberOperation();
//                if (numberOperation == 1) {
//                    //выводим баланс
//                    System.out.println(account.accountsBalance(accountNumber, pin));
//
//                    //дебет
//                } else if (numberOperation == 2) {
//                    System.out.println("Введите сумму пополнения счета");
//                    BigDecimal sumByDebit = scanner.nextBigDecimal();
//                    client.debit(sumByDebit);
//                    System.out.println("Операция проведена успешно");
//                    //credit
//                } else if (numberOperation == 3) {
//                    System.out.println("Введите сумму снятия со счета");
//                    BigDecimal sumByDebit = scanner.nextBigDecimal();
//                    int equal = sumByDebit.compareTo(account.accountsBalance(accountNumber, pin));//срваниваем суммус нятия с балансом на счете
//
//                    BigDecimal subtract = account.accountsBalance(accountNumber, pin).subtract(sumByDebit);  //разница баланса и запрашиваемой суммы
//
//
//                    if (equal == -1 || equal == 0) {
//                        System.out.println(accountNumber);
//                        client.creditOperation(subtract, client.getIdAccounts(accountNumber, pin));
//                        System.out.println("Операция проведена успешно");
//                    } else {
//                        System.out.println("Недостаточно средств");
//                    }
//                }
//                if (numberOperation == 5) {
//
//                }
//
//
//                out.println(accountNumber);
//                out.println(pin);
//                out.flush();
//
//
//            }

            // closing the scanner object

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void creditOperation(BigDecimal balanceAfterCredit, Integer getIdAccount) throws SQLException {

        String sqlAccDebitByAccAndPin = "UPDATE accounts SET Balance = " + balanceAfterCredit + " WHERE IdAccount = " + getIdAccount;
        PreparedStatement preparedStatement = conn.prepareStatement(sqlAccDebitByAccAndPin);

       preparedStatement.executeUpdate();



    }


    public Integer getIdAccounts(Integer accNumber, Integer pin) {
        Integer idAcc = null;
        String getIdAccount = "SELECT IdAccount FROM accounts where AccountNumber = " + accNumber + " AND PinCode = " + pin;
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            PreparedStatement preparedStatement = conn.prepareStatement(getIdAccount);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                idAcc = resultSet.getInt("IdAccount");

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return idAcc;
    }

    public void debit(BigDecimal sumByDebit,Integer login,Integer pin) throws SQLException {
        BigDecimal balance = account.accountsBalance(login, pin);
        BigDecimal newBalance = balance.add(sumByDebit);
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            Integer getIdAccount = getIdAccounts(login, pin);
            String sqlAccDebitByAccAndPin = "UPDATE accounts SET Balance = " + newBalance + " WHERE IdAccount = " + getIdAccounts(login, pin);
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
            ResultSet resultSet = preparedStatement.executeQuery();
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