package test.repository.transactionRepo;


import java.sql.SQLException;

import java.sql.Date;
import java.sql.Timestamp;

public interface TransactionRepo {

    void transactionNameAndDateSave(Integer transactionId, Integer idAccount, Timestamp date) throws SQLException;
    Integer getTransactionId(Integer numberOperation);

}
