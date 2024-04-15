package test.crud;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static test.utility.DBConnection.conn;

public class CRUDAccount {
    private static String sqlCreateTableAccounts = "CREATE TABLE Account (\n" +
            "accountId INT PRIMARY KEY AUTO_INCREMENT,\n" +
            " numberAccount INT,\n" +
            " passwordAccount INT,\n" +
            " createAccounts DATE,\n" +
            " timeOfAction DATE,\n" +
            " balance Decimal (65,2),\n" +
            " bankId INT)" ;
    private static String sqlInsertAccountsTable = "INSERT INTO Account (numberAccount, passwordAccount, balance, createAccounts,timeOfAction,bankId) Values (?,?,?,?,?,?,?)";


    public  void createTableAccounts() throws SQLException {
        Statement statement = conn.createStatement();
        // создание таблицы
        statement.executeUpdate(sqlCreateTableAccounts);
        System.out.println("Database has been created!");
    }
    public Integer getIdAccounts(Integer accNumber, Integer pin) throws SQLException {
        Integer idAcc = null;
        String getIdAccount = "SELECT accountId FROM account where numberAccount = " + accNumber + " AND passwordAccount = " + pin;
        PreparedStatement preparedStatement = conn.prepareStatement(getIdAccount);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            idAcc = resultSet.getInt("accountId");
        }
        return idAcc;
    }


}
