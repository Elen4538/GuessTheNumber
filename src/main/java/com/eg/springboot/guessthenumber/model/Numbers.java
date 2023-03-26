package com.eg.springboot.guessthenumber.model;

import jakarta.persistence.*;

/**
 * @author elena
 */

@Entity
@Table(name = "user_numbers")
public class Numbers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "user_number")
    private int userNumber;

    public Numbers() {
    }

    public Numbers(int userId, int userNumber) {
        this.userId = userId;
        this.userNumber = userNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public int getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(int userNumber) {
        this.userNumber = userNumber;
    }
}