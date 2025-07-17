package com.ecommercenext.nextecommerce.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role {


    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "role")
    private String role;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
