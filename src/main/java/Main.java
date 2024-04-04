import acc.Account;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.SQLException;

public class Main {


    public static void main(String[] args) throws IOException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, SQLException {


//        MyThread1 thread1 = new MyThread1();
//        MyThread1 thread2 = new MyThread1();
//        MyThread1 thread3 = new MyThread1();
//        MyThread1 thread4 = new MyThread1();
//
//        thread1.start();
//        thread2.start();
//        thread3.start();
//        thread4.start();

//        MyThread2 myThread1 = new MyThread2();
//        MyThread2 myThread2 = new MyThread2();
//        MyThread2 myThread3 = new MyThread2();
//        MyThread2 myThread4 = new MyThread2();
//
//        myThread1.start();
//        myThread2.start();
//        myThread3.start();
//        myThread4.start();


        Client.clientService();


    }

}

class MyThread1 extends Thread {
    @Override
    public void run() {
        Client account = new Client();
        BigDecimal bigDecimal = null;
        try {
         account.creditOperation(BigDecimal.valueOf(200),1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}

class MyThread2 extends Thread {
    @Override
    public void run() {
        Account account = new Account();
        try {
            account.accountsBalance(12345678,1234);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
