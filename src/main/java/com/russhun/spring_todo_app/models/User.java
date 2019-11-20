package com.russhun.spring_todo_app.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "user")
public class User implements Serializable {


    private static final long serialVersionUID = 2L;

    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "role_id")
    private int role_id;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<Memo> memos;

    public User(){}

    public User(String name, int role_id, String password) {
        this.name = name;
        this.role_id = role_id;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleId() {
        return role_id;
    }

    public void setRoleId(int role_id) {
        this.role_id = role_id;
    }
}
