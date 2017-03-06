/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.muic.ooc.webapp.api.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 *
 * @author gigadot
 */
@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String username;

//    private String name;

    private String hashedPassword;

    @ElementCollection
    @CollectionTable(name = "join_user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    private Set<String> roles = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "join_user_friend", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "friend")
    private Set<String> friends = new HashSet<>();

    @OneToMany(cascade = CascadeType.REMOVE)
    @CollectionTable(name = "user_profile", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "firstname")
    @Column(name = "lastname")


    @OneToMany(cascade = CascadeType.REMOVE)
    @CollectionTable(name = "user_credential", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "hashed_password")
    @Column(name = "created")


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public Set<UserFriend> getFriends() {
        return Friends;
    }

    public void setFriends(Set<UserFriend> Friends) {
        this.Friends = Friends;
    }

}
