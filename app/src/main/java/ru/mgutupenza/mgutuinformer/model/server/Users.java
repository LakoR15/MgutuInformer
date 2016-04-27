package ru.mgutupenza.mgutuinformer.model.server;

import java.util.UUID;

public class Users {

    private Long usersId;
    private String secretKey;
    private String name;
    private Groups groups;
    private String login;
    private String password;
    private Boolean student;

    public Users(String name, Groups groups, String login, String password, Boolean student) {
        this.name = name;
        this.groups = groups;
        this.login = login;
        this.password = password;
        this.student = student;
        this.secretKey = UUID.randomUUID().toString();
    }

    public Users() {
    }

    public Users(String login, String password) {
        this.login = login;
        this.password = password;
        this.secretKey = UUID.randomUUID().toString();
    }

    public Groups getGroups() {
        return groups;
    }

    public void setGroups(Groups groups) {
        this.groups = groups;
    }

    public Long getUsersId() {
        return usersId;
    }

    public void setId(Long id) {
        this.usersId = id;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Boolean getStudent() {
        return student;
    }

    public void setStudent(Boolean student) {
        this.student = student;
    }
}
