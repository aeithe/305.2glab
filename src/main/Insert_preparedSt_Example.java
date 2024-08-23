package main;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;

import static java.sql.DriverManager.getConnection;

public class Insert_preparedSt_Example {
    public static void main(String[] args) {
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String dburl= "jdbc:mysql://localhost:3306/classicmodels";
        String user = "root";
        String password = "MaVh3nan!";
        try {
            con = DriverManager.getConnection(dburl, user, password);
            System.out.println("Connection established");
            String sqlQuery = "Insert into employees (officeCode,firstName," +
                    "lastName,email,extension,reportsTo,VacationHours,employeeNumber,jobTitle)" +
                    " VALUES (?,?,?,?,?,?,?,?,?)";
            preparedStatement = con.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, 6);
            preparedStatement.setString(2, "Jamil");
            preparedStatement.setString(3, "fink");
            preparedStatement.setString(4, "JJ@gmail.com");
            preparedStatement.setString(5, "2759");
            preparedStatement.setInt(6, 1143);
            preparedStatement.setInt(7, 9);
            preparedStatement.setInt(8, 0003);
            preparedStatement.setString(9, "Manager");
            int affectedRows = preparedStatement.executeUpdate();
            System.out.println(affectedRows + " row(s) affected!");
            preparedStatement = con.prepareStatement("select * from employees where employeeNumber = ?");
            preparedStatement.setInt(1, 0003);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getString("firstName"));
                System.out.println(resultSet.getString("lastName"));
                System.out.println(resultSet.getString("email"));
                System.out.println(resultSet.getString("officeCode"));
            }

        }
        catch (SQLException e){
            e.printStackTrace();
        }
        try{
            preparedStatement.close();
            con.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
