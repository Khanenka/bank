import lombok.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int idUser;
    private String userFirstName;
    private String userLastName;
    private Date dateOfBirdth;

public static void createTableUsers(){
    try{
        String url = "jdbc:mysql://localhost/bank?serverTimezone=Europe/Moscow&useSSL=false";
        String username = "leonid";
        String password = "1234";
        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        // команда создания таблицы
        String sqlCommand = "CREATE TABLE users (IdUser INT PRIMARY KEY AUTO_INCREMENT, FirstName VARCHAR(20), LastName VARCHAR(20),CreateUserDate DATE)";

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

}
