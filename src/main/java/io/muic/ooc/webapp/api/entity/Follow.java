package io.muic.ooc.webapp.api.entity;

import io.muic.ooc.webapp.api.entity.auditing.BaseEntity;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by tyeon on 3/13/17.
 */
@Entity
@Where(clause = "is_active = 1")
public class Follow extends BaseEntity {
    @Transient
    public static final String SINGULAR = "follow";
    @Transient
    public static final String PLURAL = SINGULAR + "s";

    @Id
    @GeneratedValue
    private long id;

    @OneToOne
    @JoinColumn(name = "user")
    private User user;

    @OneToMany
    @JoinColumn(name = "following")
    Set<User> following = new HashSet<>();

    public long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<User> getFollowing() {
        return following;
    }

    public void setFollowing(Set<User> following) {
        this.following = following;
    }
}
