/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.muic.ooc.webapp.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.muic.ooc.webapp.api.entity.auditing.BaseEntity;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 *
 * @author gigadot
 */
@Entity
@Where(clause = "is_active = 1")
public class User extends BaseEntity {
    @Transient
    public static final String SINGULAR = "user";
    @Transient
    public static final String PLURAL = SINGULAR + "s";

    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true)
    private String studentId;

    private String firstname;

    private String lastname;

    private String hashedPassword;

    @ManyToOne
    @JoinColumn(name = "user_group")
    private UserGroup userGroup;

    public long getId() {
        return id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    @JsonIgnore
    public UserGroup getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(UserGroup userGroup) {
        this.userGroup = userGroup;
    }
}
