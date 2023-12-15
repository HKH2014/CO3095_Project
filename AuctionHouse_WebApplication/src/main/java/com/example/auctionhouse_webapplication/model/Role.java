package com.example.auctionhouse_webapplication.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.List;
import java.util.Objects;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer id;
    @Column(name = "role")
    @Enumerated(value = EnumType.STRING)
    private RoleEnum roleEnum;
    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    public Role() {
    }

    public Role(RoleEnum role) {
        this.roleEnum = role;
    }

    public Role(RoleEnum role, List<User> users) {
        this.roleEnum = role;
        this.users = users;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RoleEnum getRole() {
        return roleEnum;
    }

    public void setRole(RoleEnum role) {
        this.roleEnum = role;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Role role1 = (Role) o;
        return Objects.equals(id, role1.id) && Objects.equals(roleEnum, role1.roleEnum) && Objects.equals(users,
                                                                                                          role1.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roleEnum, users);
    }
}