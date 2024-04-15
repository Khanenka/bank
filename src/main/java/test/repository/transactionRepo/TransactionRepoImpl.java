package test.repository.transactionRepo;


import test.Account;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static test.utility.DBConnection.conn;

public class TransactionRepoImpl implements TransactionRepo{
    @Override
    public void transactionNameAndDateSave(Integer transactionId,Integer idAccount,String date) throws SQLException {

        String sqlAccDebitByAccAndPin = "INSERT INTO transaction_account (transactionId,idAccount,transactionDate) VALUES (?,?,?)";



        PreparedStatement preparedStatement = conn.prepareStatement(sqlAccDebitByAccAndPin);
        preparedStatement.setInt (1,transactionId);
        preparedStatement.setInt (2,idAccount );
        preparedStatement.setString(3, date);
        System.out.println(sqlAccDebitByAccAndPin);
        preparedStatement.execute();



        System.out.println("Transacation save"+date);
    }

   public Integer getTransactionId(Integer numberOperation){
        Integer transactionId = null;
        if(numberOperation==1){
            transactionId=3;
        }else if(numberOperation==2){
            transactionId=1;
        }else if(numberOperation==3){
            transactionId=2;
        }
        return transactionId;

    }
}
