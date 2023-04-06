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
        sb.setLength(sb.length() - 2); // remove trailing comma and space
        sb.append(")");

        StringBuilder dsb = new StringBuilder();
        dsb.append("DROP TABLE").append(" ").append(tableName).append(";");

        String createTableSQL = sb.toString();
        String dropTable = dsb.toString();

        System.out.println(dropTable);


                try{
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex){
            System.out.println(ex.getMessage());
        }

        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {
            statement.execute(createTableSQL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}