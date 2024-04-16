CREATE TABLE bank.account (accountId INT PRIMARY KEY AUTO_INCREMENT,
 numberAccount INT,
 passwordAccount INT,
 createAccounts DATE,
timeOfAction DATE,
balance Decimal (65,2),
bankId INT,
FOREIGN KEY (bankId) REFERENCES bank.bank(idBank)
);

