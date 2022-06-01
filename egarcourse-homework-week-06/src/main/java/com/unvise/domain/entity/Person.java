package com.unvise.domain.entity;

public class Person {

    private Integer id;

    private String name;

    private String phone;

    private String email;

    private String login;

    private String password;

    private Account account;

    public Person() {
    }

    public Person(Integer id,
                  String name,
                  String phone,
                  String email,
                  String login,
                  String password,
                  Account account) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.login = login;
        this.password = password;
        this.account = account;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", login='" + login + '\'' +
                ", account=" + account +
                '}';
    }

}
