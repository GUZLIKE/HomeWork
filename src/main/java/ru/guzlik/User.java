package ru.guzlik;


@Table(name = "users")
public class User {

    private static Integer nextId = 0;
    @Column(name = "id",
            type = "SERIAL")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "password")
    private String passwod;

    public User(String name, String passwod) {
        this.id = ++nextId;
        this.name = name;
        this.passwod = passwod;
    }

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
