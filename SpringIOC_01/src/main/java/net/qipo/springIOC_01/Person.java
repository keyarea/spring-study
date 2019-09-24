package net.qipo.springIOC_01;

import jdk.nashorn.internal.codegen.ObjectClassGenerator;

import java.util.List;
import java.util.Map;
import java.util.Properties;

public class Person {
    private String name = "小明";
    private Integer age;
    private String gender;
    private String email;

    private Car car;
    private List<Book> books;
    private Map<String, Object>  maps;
    private Properties properties;

    public Person(String name, Integer age, String gender, String email) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.email = email;
        System.out.println("有参构造...");
    }

    public Person(String name, Integer age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public Person(String name, String email, String gender) {
        this.name = name;
        this.gender = gender;
        this.email = email;
    }

    public Person() {
        super();
        System.out.println("person创建了");
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Map<String, Object> getMaps() {
        return maps;
    }

    public void setMaps(Map<String, Object> maps) {
        this.maps = maps;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", car=" + car +
                ", books=" + books +
                ", maps=" + maps +
                ", properties=" + properties +
                '}';
    }
}
