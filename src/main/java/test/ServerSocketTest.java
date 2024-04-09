package test;


import acc.Account;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

public class ServerSocketTest extends Thread {
    Socket serverClient;
    int clientNo;


    public static void main(String[] args) throws IOException, SQLException {
        try {
            ServerSocket server = new ServerSocket(4444);
            int counter = 0;
            System.out.println("Server Started ....");
            while (true) {
                counter++;
                Socket serverClient = server.accept();  //server accept the client connection request
                System.out.println(" >> " + "Client No:" + counter + " started!");
                ServerSocketTest sct = new ServerSocketTest(serverClient, counter); //send  the request to a separate thread
                sct.start();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    ServerSocketTest(Socket inSocket, int counter) {
        serverClient = inSocket;
        clientNo = counter;
    }

    public void run() {
        try {


            DataInputStream in = new DataInputStream(serverClient.getInputStream());
            DataOutputStream out = new DataOutputStream(serverClient.getOutputStream());
            Client client = new Client();
            Account account = new Account();


            while (true) {

                Integer login = in.readInt();
                Integer password = in.readInt();
                System.out.println(login + "  " + password);


                Integer numberOperation = in.readInt();
                System.out.println(numberOperation);

                if (numberOperation == 1) {
                    BigDecimal balance = account.accountsBalance(login, password);
                    System.out.println(balance);
                    out.writeUTF(String.valueOf(balance));
                } else if (numberOperation == 2) {
                    BigDecimal sumByDebit = BigDecimal.valueOf(Long.parseLong(in.readUTF()));
                    System.out.println(sumByDebit);

                    client.debit(sumByDebit, login, password);

                    System.out.println("Операция проведена успешно");

                } else if (numberOperation == 3) {
                    BigDecimal sumByCrebit = BigDecimal.valueOf(Long.parseLong(in.readUTF()));
                    int equal = sumByCrebit.compareTo(account.accountsBalance(login, password));//срваниваем суммус нятия с балансом на счете

                    BigDecimal subtract = account.accountsBalance(login, password).subtract(sumByCrebit);  //разница баланса и запрашиваемой суммы


                    if (equal == -1 || equal == 0) {

                        client.creditOperation(subtract, client.getIdAccounts(login, password));
                        out.writeUTF("Операция проведена успешно");
                        System.out.println("Операция проведена успешно");
                    } else {
                        out.writeUTF("Недостаточно средств");
                        System.out.println("Недостаточно средств");
                    }
                } else if (numberOperation == 4) {

                    Integer numberCardByCredit = in.readInt();
                    BigDecimal sumByDedit = BigDecimal.valueOf(Long.parseLong(in.readUTF()));

                    int equal = sumByDedit.compareTo(account.accountsBalance(login, password));//срваниваем суммус нятия с балансом на счете

                   if (equal == -1 || equal == 0) {
                        client.moneyTransaction(login, password, numberCardByCredit, sumByDedit);
                        out.writeUTF("Операция проведена успешно");
                        System.out.println("Операция проведена успешно");
                    } else {

                       out.writeUTF("Недостаточно средств");
                       System.out.println("Недостаточно средств");
                   }
                }

            }
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            System.out.println("Client -" + clientNo + " exit!! ");
        }
    }
}
