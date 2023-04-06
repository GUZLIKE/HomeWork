package ru.guzlik;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    static final String USERNAME = "postgres";
    static final String PASSWORD = "123";




    public static void main(String[] args) {

        Class<User> userClass = User.class;
        Table tableAnnotation = userClass.getAnnotation(Table.class);
        String tableName = tableAnnotation.name();
        Field[] fields = userClass.getDeclaredFields();
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE ").append(tableName).append(" (");
        for (Field field : fields) {
            Column columnAnnotation = field.getAnnotation(Column.class);
            if (columnAnnotation != null) {
                String columnName = columnAnnotation.name();
                sb.append(columnName).append(" ");
                String columnType = columnAnnotation.type();
                sb.append(columnType).append(" ");
                // add column constraints here
                sb.append(", ");
            }
        }


        //        try{
//            Class.forName("org.postgresql.Driver");
//        } catch (ClassNotFoundException ex){
//            System.out.println(ex.getMessage());
//        }
//
//        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
//             Statement statement = connection.createStatement()) {
//            statement.execute("delete from users where id = 2;");
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
    }
}