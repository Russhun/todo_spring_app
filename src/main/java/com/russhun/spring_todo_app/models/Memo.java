package com.russhun.spring_todo_app.models;

import javax.persistence.*;


@Entity
@Table(name = "memo")
public class Memo {


    @Id
    @GeneratedValue
    private int id;

    @Column(name = "text")
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_name")
    private User user;

    public Memo(){}

    public Memo(String text) {
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
