package test.repository.transactionRepo;


import test.Account;

import java.sql.SQLException;

public interface TransactionRepo {

    void transactionNameAndDateSave(Integer transactionId,Integer idAccount,String date) throws SQLException;
    Integer getTransactionId(Integer numberOperation);

}
