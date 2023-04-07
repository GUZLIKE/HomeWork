package ru.guzlik;

import ru.guzlik.DataTools.SelectData;

import java.sql.*;

public class Main {
    static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    static final String USERNAME = "postgres";
    static final String PASSWORD = "123";




    public static void main(String[] args) throws IllegalAccessException {
        User user = new User(2,"GEG","123");
        Class<? extends User> userClas = user.getClass();
        SelectData selectData = new SelectData(userClas);

        System.out.println(selectData.createTable());
        System.out.println(selectData.selectTable());
        System.out.println(selectData.deleteTable());
        System.out.println(selectData.insertInto(user));
        System.out.println(selectData.deleteFrom(user));
        System.out.println(selectData.updateColumn(user,1));


                try{
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex){
            System.out.println(ex.getMessage());
        }

        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {
            statement.execute(selectData.updateColumn(user,1));

                ResultSet rs = statement.executeQuery(selectData.selectTable());
                while (rs.next()){
                    System.out.println(rs.getInt(1));
                    System.out.println(rs.getString(2));
                    System.out.println(rs.getString(3));
                }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}