package test.repository.accountRepo;

import test.Account;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;

public interface AccountRepo {

    Account accInfoByNumberAndPin(Integer accountNumber, Integer pinCode) throws SQLException;
    BigDecimal accountsBalance(Integer accountNumber, Integer pinCode) throws SQLException;
    BigDecimal accountsBalanceByAccountNumber(Integer accountNumber) throws SQLException;
    ArrayList<Integer> inputAccAndPin();
}
