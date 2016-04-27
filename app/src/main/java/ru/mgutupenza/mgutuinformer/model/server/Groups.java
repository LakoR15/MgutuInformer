package ru.mgutupenza.mgutuinformer.model.server;

import java.util.Set;

public class Groups {

    private Long groupsId;
    private String groupsName;
    private Set<Users> users;
    private Set<Schedule> schedule;

    public Groups() {
    }

    public Groups(String groupsName) {
        this.groupsId = getGroupsId();
        this.groupsName = groupsName;
    }

    public Long getGroupsId() {
        return groupsId;
    }

    public void setGroupsId(Long groupsId) {
        this.groupsId = groupsId;
    }

    public String getGroupsName() {
        return groupsName;
    }

    public void setGroupsName(String groupsName) {
        this.groupsName = groupsName;
    }

    public Set<Users> getUsers() {
        return users;
    }

    public void setUsers(Set<Users> users) {
        this.users = users;
    }

}
