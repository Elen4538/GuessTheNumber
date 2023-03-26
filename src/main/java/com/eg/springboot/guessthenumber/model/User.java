package com.eg.springboot.guessthenumber.model;

import jakarta.persistence.*;

import java.util.List;

/**
 * @author elena
 */


// add relationships
@Entity
@Table(name = "user_info")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String userName;
    @Column(name = "computer_number")
    private int computerNumber;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<Numbers> numbers;

    public User() {
    }

    public User(String userName, int computerNumber) {
        this.userName = userName;
        this.computerNumber = computerNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getComputerNumber() {
        return computerNumber;
    }

    public void setComputerNumber(int computerNumber) {
        this.computerNumber = computerNumber;
    }
}
