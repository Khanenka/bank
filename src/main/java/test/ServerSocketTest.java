package test;


import acc.Account;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class ServerSocketTest {


  public static void main(String[] args) throws IOException, SQLException {

    ServerSocket serverSocket = new ServerSocket(4444);
    Socket socket = serverSocket.accept();

    DataInputStream in = new DataInputStream(socket.getInputStream());
    DataOutputStream out = new DataOutputStream(socket.getOutputStream());
    Integer login = null;
    Integer password = null;
    Account account=new Account();

    while (true) {

      login=in.readInt();
     password= in.readInt();
      System.out.println(login+"  "+password);

      BigDecimal balance = account.accountsBalance(login, password);
      System.out.println(balance);
      out.writeUTF(String.valueOf(balance));

    }
  }
}
