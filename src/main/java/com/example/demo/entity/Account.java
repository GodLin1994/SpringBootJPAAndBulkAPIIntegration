package com.example.demo.entity;

import org.hibernate.annotations.Table;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@javax.persistence.Table(name="db_account")
public class Account {
    @Id
    public String id;
    public String Name;
    public String Phone;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public Account(String id, String name, String phone) {

        this.id = id;
        Name = name;
        Phone = phone;
    }

    public Account() {
    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", Name='" + Name + '\'' +
                ", Phone='" + Phone + '\'' +
                '}';
    }
}
