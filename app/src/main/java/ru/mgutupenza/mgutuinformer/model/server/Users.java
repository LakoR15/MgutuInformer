package ru.mgutupenza.mgutuinformer.model.server;

public class Users {

    private Long usersId;
    private String secretKey;
    private String name;
    private Groups groups;
    private Boolean student;

    public Users() {
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

    public Boolean getStudent() {
        return student;
    }

    public void setStudent(Boolean student) {
        this.student = student;
    }
}
