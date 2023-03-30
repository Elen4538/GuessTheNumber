package com.eg.springboot.guessthenumber.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author elena
 */


 //add relationships
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

    @OneToMany(cascade = CascadeType.PERSIST) // CascadeType.ALL
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
// validation
    //@NotBlank
    //@Pattern (regexp = "\\d{3}-\\d{2}-\\d{2}", message = "please use pattern XXX-XX-XX")
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

    @Override
    public String toString() {

        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", computerNumber=" + computerNumber +
                ", numbers=" + numbers +
                '}';
    }
}
//
//
//@Entity
//@Table(name = "users")
//public class User {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(nullable = false)
//    private String userName;
//
//    @Column(nullable = false)
//    private int computerNumber;
//
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private List<Numbers> numbers = new ArrayList<>();
//
//    public User() {
//    }
//
//    public User(String userName, int computerNumber) {
//        this.userName = userName;
//        this.computerNumber = computerNumber;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
//
//    public int getComputerNumber() {
//        return computerNumber;
//    }
//
//    public void setComputerNumber(int computerNumber) {
//        this.computerNumber = computerNumber;
//    }
//
//    public List<Numbers> getNumbers() {
//        return numbers;
//    }
//
//    public void setNumbers(List<Numbers> numbers) {
//        this.numbers = numbers;
//    }
//
//    public void addNumber(Numbers number) {
//        numbers.add(number);
//        number.setUser(this);
//    }
//
//    @Override
//    public String toString() {
//        return "User{" +
//                "id=" + id +
//                ", userName='" + userName + '\'' +
//                ", computerNumber=" + computerNumber +
//                ", numbers=" + numbers +
//                '}';
//    }
//}
