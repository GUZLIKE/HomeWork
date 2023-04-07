package ru.guzlik;


import ru.guzlik.Annotation.Column;
import ru.guzlik.Annotation.Id;
import ru.guzlik.Annotation.Table;
import ru.guzlik.DataTools.DataType;

@Table(name = "users")
public class User {

    @Id
    @Column(name = "id",
            type = DataType.SERIAL)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "password")
    private String passwod;


    public User(Integer id, String name, String passwod) {
        this.id = id;
        this.name = name;
        this.passwod = passwod;
    }

    public User() {}


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswod() {
        return passwod;
    }

    public void setPasswod(String passwod) {
        this.passwod = passwod;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", passwod='" + passwod + '\'' +
                '}';
    }
}
