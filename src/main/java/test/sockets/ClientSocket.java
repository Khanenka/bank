package test.sockets;

import test.Account;
import test.repository.accountRepo.AccountRepoImpl;
import test.services.AccountService;

import java.io.*;
import java.math.BigDecimal;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import static test.services.AccountService.scanner;


public class ClientSocket {

    AccountRepoImpl accountRepo=new AccountRepoImpl();
    AccountService accountService=new AccountService();

 public void test() throws IOException {
     Socket clientSocket = new Socket("localhost", 4444);
     System.out.println("Hello");
 }

public void serviceClientSocket() throws IOException {
    Socket clientSocket = new Socket("localhost", 4444);
    OutputStream outToServer = clientSocket.getOutputStream();
    DataOutputStream out = new DataOutputStream(outToServer);

    InputStream inFromServer = clientSocket.getInputStream();
    DataInputStream in = new DataInputStream(inFromServer);
    Account account = new Account();



    Integer login = null;
    Integer password = null;
    Integer numberAccount = account.getNumberAccount();
    Integer passwordAccount = account.getPasswordAccount();


    while (true) {

        ArrayList<Integer> arrayList = accountRepo.inputAccAndPin();
        System.out.println(arrayList);
        for (int i = 0; i < arrayList.size(); i++) {

            numberAccount = arrayList.get(0);
            passwordAccount = arrayList.get(1);
            out.writeInt(arrayList.get(i));
        }




        int numberOperation = accountService.numberOperation();
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

        } else if(numberOperation == 4){
            System.out.println("Введите номер карты пополнения");
            Integer nymberCardDedit = scanner.nextInt();
            out.writeInt(nymberCardDedit);
            System.out.println("Введите сумму пополнения");
            BigDecimal sumByDebit = scanner.nextBigDecimal();
            out.writeUTF(String.valueOf(sumByDebit));

            System.out.println(in.readUTF());
        }


    }
}
    public static void main(String[] args) throws IOException, SQLException {


      ClientSocket clientSocket=new ClientSocket();
      clientSocket.serviceClientSocket();

    }
}
