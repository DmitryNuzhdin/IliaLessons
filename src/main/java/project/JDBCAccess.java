package project;

/**
 * @author Ilia Moskalenko
 */

import java.sql.*;

public class JDBCAccess {
    public static void main(String[] args){
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:h2:/home/ilia/home/IliaLessons");
            Statement statement = connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
