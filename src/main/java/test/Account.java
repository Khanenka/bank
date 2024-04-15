package test;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import static test.utility.DBConnection.conn;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Account {

   private Integer accountId;
   private Integer numberAccount;
   private Integer passwordAccount;
   private BigDecimal balance;
   private Date createAccounts;
   private Date timeOfAction;
   private Integer bankId;






   @Override
   public String toString() {
      return "Account{" +
              "accountId=" + accountId +
              ", numberAccount=" + numberAccount +
              ", passwordAccount=" + passwordAccount +
              ", balance=" + balance +
              ", createAccounts=" + createAccounts +
              ", timeOfAction=" + timeOfAction +
              ", bankId='" + bankId + '\'' +
              '}';
   }
}
