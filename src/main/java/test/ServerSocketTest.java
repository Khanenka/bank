package test;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class ServerSocketTest {


    public static void main(String[] args) throws IOException {


        ServerSocket serverSocket = new ServerSocket(4444);
        Socket socket = serverSocket.accept();

        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());


        while (true) {


            System.out.println(in.readInt());

        }
    }
}
