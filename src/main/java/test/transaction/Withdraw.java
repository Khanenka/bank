package test.transaction;

import test.Account;
import test.crud.CRUDAccount;
import test.repository.accountRepo.AccountRepoImpl;
import test.utility.DBConnection;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Withdraw {

    AccountRepoImpl accountRepo=new AccountRepoImpl();
    Account account;
    CRUDAccount crudAccount=new CRUDAccount();
    Connection connection=DBConnection.test();
    public void debit(BigDecimal sumByDebit, Account account) throws SQLException {

        BigDecimal balance = accountRepo.accountsBalance(account.getNumberAccount(),account.getPasswordAccount());
        BigDecimal newBalance = balance.add(sumByDebit);
        Integer getIdAccount = crudAccount.getIdAccounts(account.getNumberAccount(),account.getPasswordAccount());
        String sqlAccDebitByAccAndPin = "UPDATE account SET balance = " + newBalance + " WHERE accountId = " + getIdAccount;
        PreparedStatement preparedStatement = connection.prepareStatement(sqlAccDebitByAccAndPin);
        preparedStatement.executeUpdate();
    }
    public void moneyTransaction(Account account, Integer loginIn, BigDecimal sumTransaction) throws SQLException {
        Integer numberAccount = account.getNumberAccount();
        Integer passwordAccount = account.getPasswordAccount();
        BigDecimal balanceOut = accountRepo.accountsBalance(numberAccount,passwordAccount);
        BigDecimal balanceIn = accountRepo.accountsBalanceByAccountNumber(loginIn);

        String sqlCreditOperation = "UPDATE account SET balance = " + balanceOut.subtract(sumTransaction) + "WHERE accountId = " +  crudAccount.getIdAccounts(account.getNumberAccount(),account.getPasswordAccount());
        String sqlDebitOperation = "UPDATE account SET balance = " + balanceIn.add(sumTransaction) + "WHERE numberAccount = " + loginIn;

        int equal = sumTransaction.compareTo(accountRepo.accountsBalance(numberAccount, passwordAccount));



        if (equal == -1 || equal == 0) {
            PreparedStatement preparedStatement1 = connection.prepareStatement(sqlCreditOperation);
            PreparedStatement preparedStatement2 = connection.prepareStatement(sqlDebitOperation);
            preparedStatement1.executeUpdate();
            preparedStatement2.executeUpdate();
        } else {
            System.out.println("Недостаточно средств");
        }
    }


    public void creditOperation(BigDecimal balanceAfterCredit, Account account) throws SQLException {
        Integer id =crudAccount.getIdAccounts(account.getNumberAccount(),account.getPasswordAccount());
        String sqlAccDebitByAccAndPin = "UPDATE account SET balance = " + balanceAfterCredit + " WHERE accountId = " + id;
        System.out.println(sqlAccDebitByAccAndPin);
        PreparedStatement preparedStatement = connection.prepareStatement(sqlAccDebitByAccAndPin);

        preparedStatement.executeUpdate();


    }


}
