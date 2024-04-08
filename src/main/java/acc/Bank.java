package acc;

import lombok.*;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Scanner;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Bank {

    private int idBank;
    private BankName bankName;
//    private Currency currency;
//    private BigDecimal exchangeRate;
    private int idAccount;



    static String url = "jdbc:mysql://localhost/bank?serverTimezone=Europe/Moscow&useSSL=false";
    static String username = "leonid";
    static String password = "1234";
    static Scanner scanner = new Scanner(System.in);


    public static void createTableBank(){
        try{
//            String url = "jdbc:mysql://localhost/bank?serverTimezone=Europe/Moscow&useSSL=false";
//            String username = "leonid";
//            String password = "1234";
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            // команда создания таблицы
            String sqlCommand = "CREATE TABLE Bank (IdBank INT PRIMARY KEY AUTO_INCREMENT, BankName VARCHAR(20))";

            try (Connection conn = DriverManager.getConnection(url, username, password)){

                Statement statement = conn.createStatement();
                // создание таблицы
                statement.executeUpdate(sqlCommand);

                System.out.println("Database has been created!");
            }
        }
        catch(Exception ex){
            System.out.println("Connection failed...");

            System.out.println(ex);
        }
    }

    public static void insertTable(){
        try{


            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            System.out.print("Input bankName: ");
            String bankName = scanner.nextLine();






            try (Connection conn = DriverManager.getConnection(url, username, password)){

                String sql = "INSERT INTO bank ( BankName) Values (?)";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, bankName);




                int rows = preparedStatement.executeUpdate();

                System.out.printf("%d rows added", rows);
            }
        }
        catch(Exception ex){
            System.out.println("Connection failed...");

            System.out.println(ex);
        }
    }



}
