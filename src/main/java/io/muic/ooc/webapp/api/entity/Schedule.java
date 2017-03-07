package io.muic.ooc.webapp.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.muic.ooc.webapp.api.entity.auditing.BaseEntity;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by tyeon on 3/7/17.
 */
@Entity
@Where(clause = "is_active = 1")
public class Schedule extends BaseEntity {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany
    @JoinColumn(name = "course")
    private Set<Course> courses = new HashSet<>();

    /* TODO: INCLUDE @ONETOMANY COURSE AND @MANYTOONE TRIMESTER */

    public long getId() {
        return id;
    }

    @JsonIgnore
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @JsonIgnore
    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
