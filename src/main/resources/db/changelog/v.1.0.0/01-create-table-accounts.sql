CREATE TABLE bank.account (accountId INT PRIMARY KEY AUTO_INCREMENT,
numberAccount INT,
passwordAccount INT,
createAccounts DATETIME,
timeOfAction DATETIME,
balance Decimal (65,2),
bankId INT,
FOREIGN KEY (bankId) REFERENCES bank.bank(idBank)
);

