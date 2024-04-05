package test;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientSocket {


    public static void main(String[] args) throws IOException {


        Socket clientSocket = new Socket("localhost", 4444);
        OutputStream outToServer = clientSocket.getOutputStream();
        DataOutputStream out = new DataOutputStream(outToServer);

        InputStream inFromServer = clientSocket.getInputStream();
        DataInputStream in = new DataInputStream(inFromServer);
        while (true) {

            ArrayList<Integer> arrayList = inputLoginAndPAssword();
            for (int i = 0; i <arrayList.size() ; i++) {
//                System.out.println(arrayList.get(i));
                out.writeInt(arrayList.get(i));
            }

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
