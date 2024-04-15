package test.crud;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import static test.utility.DBConnection.conn;

public class CRUDBank {

    static Scanner scanner = new Scanner(System.in);


    public void createTableBank() throws SQLException {


            // команда создания таблицы
            String sqlCommand = "CREATE TABLE Banks (idBank INT PRIMARY KEY AUTO_INCREMENT, BankName VARCHAR(20))";



                Statement statement = conn.createStatement();
                // создание таблицы
                statement.executeUpdate(sqlCommand);

                System.out.println("Database has been created!");
            }
    }


