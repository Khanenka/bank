import java.sql.*;
import java.util.Scanner;

import static acc.Account.*;

public class BankService {

    Scanner scanner = new Scanner(System.in);

    public Boolean searchAccByNumberCard() {
        ResultSet resultSet = null;
        try {


            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            System.out.print("Введите номер карты: ");
            int numberCard = scanner.nextInt();
            System.out.print("Введите пин карты: ");
            int pinCode = scanner.nextInt();

            try (Connection conn = DriverManager.getConnection(url, username, password)) {

//                    String sql = "SELECT * from accounts where AccountNumber = " + numberCard;
                String sqlAccByPin = "SELECT * FROM accounts where AccountNumber = " + numberCard + " AND PinCode = " + pinCode;
                PreparedStatement preparedStatement = conn.prepareStatement(sqlAccByPin);


                resultSet= preparedStatement.executeQuery();

                while (resultSet.next() ) {
                    Integer accountNumber = resultSet.getInt("AccountNumber");
                    Integer pinCode1 = resultSet.getInt("PinCode");
                    if(accountNumber!=null && pinCode1!=null){
                       return true;
                    }
                    return false;

                }




            }

        } catch (Exception ex) {

            System.out.println("Connection failed...");

            System.out.println(ex);
        }


       return false;
    }


}
