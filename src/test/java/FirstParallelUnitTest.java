import org.junit.experimental.ParallelComputer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.runner.JUnitCore;
import test.Client;
import test.ClientSocket;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Execution(ExecutionMode.CONCURRENT)
public class FirstParallelUnitTest extends Thread {


    @Test
    public void third8() throws Exception {
        ClientSocket clientSocket3 = new ClientSocket();
        clientSocket3.serviceClientSocket();


    }

    @Test
    public void third9() throws Exception {
        System.out.println("Main thread started...");
        for (int i = 1; i < 1000; i++) {
            new FirstParallelUnitTest().start();
            System.out.println("i" + i);


            Thread.sleep(500);

        }
    }

    ClientSocket clientSocket = new ClientSocket();

    @Override
    public void run() {

        try {
            clientSocket.test();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    }


