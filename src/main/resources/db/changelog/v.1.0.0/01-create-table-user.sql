CREATE TABLE bank.user (idUser INT PRIMARY KEY AUTO_INCREMENT,
                        userFirstName varchar(50),
                        userLastName varchar(50),
                        dateOfBirdth DATE,
                        idAccount INT,
                        FOREIGN KEY (idAccount) REFERENCES bank.account(accountId)

);



