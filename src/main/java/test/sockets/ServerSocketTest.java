package test.sockets;


import test.Account;
import test.crud.CRUDAccount;
import test.repository.accountRepo.AccountRepoImpl;
import test.repository.transactionRepo.TransactionRepo;
import test.repository.transactionRepo.TransactionRepoImpl;
import test.transaction.Withdraw;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.ServerSocket;
import java.net.Socket;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ServerSocketTest extends Thread {
    Socket serverClient;
    int clientNo;
    Withdraw withdraw=new Withdraw();
    AccountRepoImpl accountRepo=new AccountRepoImpl();
    TransactionRepo transactionRepo=new TransactionRepoImpl();
    Date date = new Date();
    CRUDAccount crudAccount=new CRUDAccount();





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
            Account account = new Account();


            while (true) {

                Integer login = in.readInt();
                Integer password = in.readInt();
                System.out.println(login + "  " + password);


                Integer numberOperation = in.readInt();
                System.out.println(numberOperation);

                account.setNumberAccount(login);
                account.setPasswordAccount(password);
                System.out.println(crudAccount.getIdAccounts(login,password));
                TransactionRepo transactionRepo1=new TransactionRepoImpl();
                Integer transactionId = transactionRepo1.getTransactionId(numberOperation);
                if (numberOperation == 1) {
                    BigDecimal balance = accountRepo.accountsBalance(login, password);
                    System.out.println(balance);
                    out.writeUTF(String.valueOf(balance));

                    transactionRepo.transactionNameAndDateSave(transactionId,crudAccount.getIdAccounts(login,password),String.valueOf(date.getTime()));

                } else if (numberOperation == 2) {
                    BigDecimal sumByDebit = BigDecimal.valueOf(Long.parseLong(in.readUTF()));
                    System.out.println(sumByDebit);

                    withdraw.debit(sumByDebit,account);
                    transactionRepo.transactionNameAndDateSave(transactionId,crudAccount.getIdAccounts(login,password),String.valueOf(date.getTime()));
                    System.out.println("Операция проведена успешно");

                } else if (numberOperation == 3) {
                    BigDecimal sumByCrebit = BigDecimal.valueOf(Long.parseLong(in.readUTF()));
                    int equal = sumByCrebit.compareTo(accountRepo.accountsBalance(login, password));//срваниваем суммус нятия с балансом на счете

                    BigDecimal subtract = accountRepo.accountsBalance(login, password).subtract(sumByCrebit);  //разница баланса и запрашиваемой суммы


                    if (equal == -1 || equal == 0) {

                        withdraw.creditOperation(subtract, account);
                        transactionRepo.transactionNameAndDateSave(transactionId,crudAccount.getIdAccounts(login,password),String.valueOf(date.getTime()));
                        out.writeUTF("Операция проведена успешно");
                        System.out.println("Операция проведена успешно");
                    } else {
                        out.writeUTF("Недостаточно средств");
                        System.out.println("Недостаточно средств");
                    }
                } else if (numberOperation == 4) {

                    Integer numberCardByCredit = in.readInt();
                    BigDecimal sumByDedit = BigDecimal.valueOf(Long.parseLong(in.readUTF()));

                    int equal = sumByDedit.compareTo(accountRepo.accountsBalance(login, password));//срваниваем суммус нятия с балансом на счете

                   if (equal == -1 || equal == 0) {
                        withdraw.moneyTransaction(account, numberCardByCredit, sumByDedit);
                       transactionRepo.transactionNameAndDateSave(transactionId,crudAccount.getIdAccounts(login,password),String.valueOf(date.getTime()));
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
