CREATE TABLE bank.transaction_account (transactionId INT ,
                                       idAccount INT,
                                       transactionDate TIMESTAMP(3),
                                       FOREIGN KEY (idAccount) REFERENCES bank.account(accountId),
                                       FOREIGN KEY (transactionId) REFERENCES bank.transaction(idTransaction)

);
