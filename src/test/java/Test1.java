

    import org.junit.jupiter.api.*;
    import test.Client;

    import java.math.BigDecimal;
    import java.sql.SQLException;

    public class Test1 {

        @BeforeAll
        public static void start() {
            System.out.println("=======Starting junit 5 tests========");
        }

        @BeforeEach
        public void setup() {
            System.out.println("=======Setting up the prerequisites========");

        }


    }


