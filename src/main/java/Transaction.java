import lombok.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Date;
import java.util.Scanner;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    private TransactionName sds;
    private Date transactionDate;


    static String url = "jdbc:mysql://localhost/bank?serverTimezone=Europe/Moscow&useSSL=false";
    static String username = "leonid";
    static String password = "1234";
    static Scanner scanner = new Scanner(System.in);

    public static void createTableTransaction(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            // команда создания таблицы
            String sqlCommand = "CREATE TABLE transaction (idTransaction INT PRIMARY KEY AUTO_INCREMENT, transactionName VARCHAR(20),transactionDate DATE)";

            try (Connection conn = DriverManager.getConnection(url, username, password)){

                Statement statement = conn.createStatement();
                // создание таблицы
                statement.executeUpdate(sqlCommand);

                System.out.println("Database has been created!");
                conn.close();
            }
        }
        catch(Exception ex){
            System.out.println("Connection failed...");

            System.out.println(ex);
        }
    }



    public static void insertTransactionTable(){
        try{


            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            System.out.print("Input name of transaction: ");
            String nameOfTransaction = scanner.next();
//            System.out.print("Input transactionDate: ");
//            Date transactionDate = new Date(System.currentTimeMillis());
//            System.out.println(transactionDate);

//         System.out.print("Input currency: ");
//         String currency = scanner.nextLine();





            try (Connection conn = DriverManager.getConnection(url, username, password)){

                String sql = "INSERT INTO transaction (transactionName) Values (?)";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);

                preparedStatement.setString(1, nameOfTransaction);





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
