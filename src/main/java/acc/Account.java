package acc;

import impl.AccountRepo;
import impl.AccountRepoImpl;
import lombok.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Scanner;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Account {

   private int idAccount;
   private int accountNumber;
   private int pinCode;
   private String bankName;
//   private Currency currency;


  public static String url = "jdbc:mysql://localhost/bank?serverTimezone=Europe/Moscow&useSSL=false";
   public static String username = "leonid";
   public static String password = "1234";
   public static Scanner scanner = new Scanner(System.in);

   public static void createTableAccounts(){
      try{
         String url = "jdbc:mysql://localhost/bank?serverTimezone=Europe/Moscow&useSSL=false";
         String username = "leonid";
         String password = "1234";
         Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
         // команда создания таблицы
         String sqlCommand = "CREATE TABLE accounts (IdAccount INT PRIMARY KEY AUTO_INCREMENT, AccountNumber INT,PinCode INT,BankId INT,FOREIGN KEY (BankId) REFERENCES Bank(IdBank))";

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



   public static void insertAccountsTable(){
      try{


         Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
         System.out.print("Input AccountNumber: ");
         int numberAccount = scanner.nextInt();
         System.out.print("Input pinCode: ");
         int pinCode = scanner.nextInt();
         System.out.println("Input bankName: ");
         int bankId = scanner.nextInt();
//         System.out.print("Input currency: ");
//         String currency = scanner.nextLine();





         try (Connection conn = DriverManager.getConnection(url, username, password)){

            String sql = "INSERT INTO accounts (AccountNumber, PinCode, BankId) Values (?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

             preparedStatement.setInt(1, numberAccount);
            preparedStatement.setInt(2, pinCode);
            preparedStatement.setInt(3, bankId);
//            preparedStatement.setString(4, currency);



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
