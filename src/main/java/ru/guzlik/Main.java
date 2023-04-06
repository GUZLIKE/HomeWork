package ru.guzlik;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.List;

public class Main {
    static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    static final String USERNAME = "postgres";
    static final String PASSWORD = "123";


    public static void main(String[] args) {

        Class<User> clazz = User.class;
        Table table = clazz.getAnnotation(Table.class);
        String tableName = table.name();
        System.out.println("Table name:" + tableName);

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Column.class)){
                Column column = field.getAnnotation(Column.class);
                String columnName = column.name();
                System.out.println("Field: " + field.getName() + ", Column name: " + columnName);
            }
        }
    }
}
