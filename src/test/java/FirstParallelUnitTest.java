import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.math.BigDecimal;

@Execution(ExecutionMode.CONCURRENT)
public class FirstParallelUnitTest {

    @Test
    public void first() throws Exception{
        Client client=new Client();

        BigDecimal sum=null;

            sum= BigDecimal.valueOf(100);
        client.getIdAccounts(12345678, 1234);

        assert client.getIdAccounts(12345678, 1234).equals(1);






        System.out.println("FirstParallelUnitTest first() end => " + Thread.currentThread().getName());
    }

    @Test
    public void second() throws Exception{
        Client client=new Client();

        BigDecimal sum=null;

            sum= BigDecimal.valueOf(200);
            client.debit(sum);





    }

}
