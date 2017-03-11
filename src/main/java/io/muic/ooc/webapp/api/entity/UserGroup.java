/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.muic.ooc.webapp.api.entity;

import io.muic.ooc.webapp.api.entity.auditing.BaseEntity;
import org.hibernate.annotations.Where;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 *
 * @author gigadot
 */
@Entity
@Where(clause = "is_active = 1")
public class UserGroup extends BaseEntity {
    @Transient
    public static final String SINGULAR = "userGroup";
    @Transient
    public static final String PLURAL = SINGULAR + "s";

    @Id
    @GeneratedValue
    private long id;

    private RoleType role;

    private String description;

    @OneToMany
    @JoinColumn(name = "user_groups")
    private Set<User> users = new HashSet<>();

    public long getId() {
        return id;
    }

    public RoleType getRole() {
        return role;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public enum RoleType {
        ADMIN,
        PROFESSOR,
        STUDENT,
    }
}
