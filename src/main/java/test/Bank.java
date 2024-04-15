package test;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Bank {

    private Integer idBank;
    private String name;


    @Override
    public String toString() {
        return "Bank{" +
                "idBank=" + idBank +
                ", name='" + name + '\'' +
                '}';
    }
}
