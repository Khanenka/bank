import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.sql.SQLException;

public class Test2 {
    @BeforeAll
    public static void start() {
        System.out.println("=======Starting junit 5 tests========");
    }
    @BeforeEach
    public void setup() {
        System.out.println("=======Setting up the prerequisites========");
    }

    @Test
    void test2_FirstTest() {
        System.out.println(Thread.currentThread().getStackTrace()[1]
                .getMethodName()+" => executed successfully");
    }
    @Test
    void test2_SecondTest() {
        System.out.println(Thread.currentThread().getStackTrace()[1]
                .getMethodName()+" => executed successfully");
    }

    @Test
    void test2_ThirdTest() {
        System.out.println(Thread.currentThread().getStackTrace()[1]
                .getMethodName()+" => executed successfully");
    }
    @Test
    void test2_FourthTest() {
        System.out.println(Thread.currentThread().getStackTrace()[1]
                .getMethodName()+" => executed successfully");
    }
    @AfterEach
    public void tearDown() {
        System.out.println("Tests ended");
    }
    @AfterAll
    public static void end() {
        System.out.println("All the tests are executed");
    }


    @Test
    public void test2() throws SQLException {
        Client client=new Client();
        Integer sum=null;
        for (int i = 0; i < 1000; i++) {
            sum=i;
            client.debit(BigDecimal.valueOf(sum));
            System.out.println("2 test");
        }


    }
}