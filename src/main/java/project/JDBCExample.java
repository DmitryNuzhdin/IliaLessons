package project;


import java.sql.*;

public class JDBCExample {

    public static void main(String[] args) {
        try {
            Class.forName("org.h2.Driver");
            //Class.forName("org.postgres.....");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:h2:C:\\Users\\Dmitry\\IdeaProjects\\IliaLessons\\testdb");
            Statement statement = conn.createStatement();
//            statement.executeUpdate("insert into abc (id) values (1)");
//            statement.executeUpdate("insert into abc (id) values (2)");
//            statement.executeUpdate("insert into abc (id) values (3)");
            ResultSet rs = statement.executeQuery("select id from abc where id = ?");

            while(rs.next()) {
                int i = rs.getInt(rs.findColumn("id"));
                System.out.println(i);
            }

            rs.close();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }



        try {
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

//        stmt = conn.createStatement();
//        String sql;
//        ResultSet rs = stmt.executeQuery(sql);
    }
}
