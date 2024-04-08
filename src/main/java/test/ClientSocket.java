package test;

import acc.Account;
import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.math.BigDecimal;
import java.net.Socket;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.ArrayList;
import java.util.Scanner;

import static acc.Account.scanner;
import static test.Client.accountNumber;

public class ClientSocket {


    public static void main(String[] args) throws IOException, SQLException {


        Socket clientSocket = new Socket("localhost", 4444);
        OutputStream outToServer = clientSocket.getOutputStream();
        DataOutputStream out = new DataOutputStream(outToServer);

        InputStream inFromServer = clientSocket.getInputStream();
        DataInputStream in = new DataInputStream(inFromServer);
        Account account = new Account();
        Client client = new Client();


        Integer login = null;
        Integer password = null;
        while (true) {

            ArrayList<Integer> arrayList = account.inputAccAndPin();
            for (int i = 0; i < arrayList.size(); i++) {
//                System.out.println(arrayList.get(i));
                login = arrayList.get(0);
                password = arrayList.get(1);
                out.writeInt(arrayList.get(i));
            }

//            System.out.println(in.readUTF());


            int numberOperation = client.numberOperation();
            out.writeInt(numberOperation);
            if (numberOperation == 1) {


                System.out.println(in.readUTF());

            } else if (numberOperation == 2) {

                System.out.println("Введите сумму пополнения счета");
                Scanner scanner = new Scanner(System.in);
                BigDecimal sumByDebit = scanner.nextBigDecimal();
                out.writeUTF(String.valueOf(sumByDebit));
            } else if (numberOperation == 3) {
                System.out.println("Введите сумму снятия со счета");
                BigDecimal sumByCredit = scanner.nextBigDecimal();
                out.writeUTF(String.valueOf(sumByCredit));
                System.out.println(in.readUTF());

            }


        }


    }
}
