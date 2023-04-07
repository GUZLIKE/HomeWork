package ru.guzlik.Annotation;

import ru.guzlik.DataTools.DataType;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Column {
    String name();
    DataType type() default DataType.VARCHAR;
}