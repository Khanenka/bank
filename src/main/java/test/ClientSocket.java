package test;

import acc.Account;
import java.io.*;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientSocket {


    public static void main(String[] args) throws IOException, SQLException {


        Socket clientSocket = new Socket("localhost", 4444);
        OutputStream outToServer = clientSocket.getOutputStream();
        DataOutputStream out = new DataOutputStream(outToServer);

        InputStream inFromServer = clientSocket.getInputStream();
        DataInputStream in = new DataInputStream(inFromServer);
        Account account=new Account();
        Integer login = null;
        Integer password = null;
        while (true) {

            ArrayList<Integer> arrayList = inputLoginAndPAssword();
            for (int i = 0; i <arrayList.size() ; i++) {
//                System.out.println(arrayList.get(i));
                login= arrayList.get(0);
                password = arrayList.get(1);
                out.writeInt(arrayList.get(i));
            }

            System.out.println(in.readUTF());

               ;
//            out.writeUTF(inputLoginAndPAssword().get(1));



        }




    }

    public static ArrayList<Integer> inputLoginAndPAssword(){
        ArrayList<Integer> arrayList= new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите логин:");
        Integer login = scanner.nextInt();
        System.out.println("Введите пароль:");
        Integer pass = scanner.nextInt();
        arrayList.add(login);
        arrayList.add(pass);

        return arrayList;
    }
}
