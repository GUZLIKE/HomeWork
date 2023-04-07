package ru.guzlik.DataTools;

import ru.guzlik.Annotation.Column;
import ru.guzlik.Annotation.Id;
import ru.guzlik.Annotation.Table;
import ru.guzlik.DataTools.DataType;

import java.lang.reflect.Field;

public class SelectData {

    Class<? extends Object> clazz;

    public SelectData(Class<? extends Object> clazz) {
        this.clazz = clazz;
    }

    public String  selectTable(){
        StringBuilder sb = new StringBuilder();
        sb
                .append("SELECT ")
                .append("*").append(" ")
                .append("FROM").append(" ")
                .append(clazz.getAnnotation(Table.class).name())
                .append(";");
        return sb.toString();
    }

    public String createTable(){
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE ").append(clazz.getAnnotation(Table.class).name())
                .append(" (");
        for (Field field : clazz.getDeclaredFields()) {
                sb.append( field.getAnnotation(Column.class).name()).append(" ");
                sb.append( field.getAnnotation(Column.class).type()).append(" ");
                sb.append(",");
            }
        sb.setLength(sb.length() - 2);
        sb.append(");");
        return sb.toString();
    }

    public String deleteTable(){
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE").append(" ").append(clazz.getAnnotation(Table.class).name()).append(";");
        return sb.toString();
    }

    public String  insertInto(Object entity) throws IllegalAccessException {
        StringBuilder sb = new StringBuilder();
        sb
                .append("INSERT INTO ")
                .append(clazz.getAnnotation(Table.class).name())
                .append(" (");
                for (Field field : clazz.getDeclaredFields()){
                    sb.append( field.getAnnotation(Column.class).name()).append(" ");
                    sb.append(",");
                }

                sb.setLength(sb.length() - 2);
                sb.append(")");
                sb
                        .append(" VALUES")
                        .append(" (");
                        for(Field i : clazz.getDeclaredFields()){
                            i.setAccessible(true);
                            if (i.getAnnotation(Column.class).type() == DataType.VARCHAR){
                                sb.append("\'");
                                sb.append(i.get(entity));
                                sb.append("\'");
                            } else {
                                sb.append(i.get(entity));
                            }
                            sb.append(", ");
                        }
                        sb.setLength(sb.length() - 2);
                        sb.append(");");


        return sb.toString();
    }

    public String deleteFrom(Object entity) throws IllegalAccessException {
        StringBuilder sb = new StringBuilder();

        sb
                .append("DELETE FROM ")
                .append(clazz.getAnnotation(Table.class).name())
                .append(" WHERE ");
                for(Field i : clazz.getDeclaredFields()){
                    i.setAccessible(true);
                    if (i.getAnnotation(Column.class).type() == DataType.VARCHAR && i.isAnnotationPresent(Column.class)){
                        sb.append("");
                    } else {
                        sb.append(i.getName())
                                .append(" = ")
                                .append(i.get(entity));
                    }
                    sb .append(";");
                }
                sb.setLength(sb.length() - 2);

                return sb.toString();
    }

    public String updateColumn(Object entity, Integer id) throws IllegalAccessException {
        Class<?> clazz = entity.getClass();
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ")
                .append(clazz.getAnnotation(Table.class).name())
                .append(" SET ");
        boolean first = true;
        Field idField = null;
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Column.class)) {
                if (!first) {
                    sb.append(", ");
                }
                first = false;
                field.setAccessible(true);
                sb.append(field.getAnnotation(Column.class).name())
                        .append(" = ");
                Object value = field.get(entity);
                if (value == null) {
                    sb.append("NULL");
                } else if (value instanceof Number) {
                    sb.append(value);
                } else {
                    sb.append('\'')
                            .append(value.toString().replace("'", "''"))
                            .append('\'');
                }
                if (field.isAnnotationPresent(Id.class)) {
                    idField = field;
                }
            }
        }
        if (idField == null) {
            throw new IllegalArgumentException("No @Id annotation found on " + clazz.getName());
        }
        idField.setAccessible(true);
        sb.append(" WHERE ")
                .append(idField.getAnnotation(Column.class).name())
                .append(" = ");
        if (id == null) {
            sb.append("NULL");
        } else {
            sb.append(id);
        }
        return sb.toString();
    }







}
