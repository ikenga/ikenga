package me.ikenga.user;

import java.util.List;

/**
 * Created by schiller on 19.06.2014.
 */
public class Team {

    String name;
    List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
