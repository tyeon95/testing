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
    @Transient
    public static final String SINGULAR = "schedule";
    @Transient
    public static final String PLURAL = SINGULAR + "s";

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

//    @ManyToMany
//    @JoinTable(name="join_course_schedule",
//            joinColumns=@JoinColumn(name="schedule_id", referencedColumnName="id"),
//            inverseJoinColumns=@JoinColumn(name="course_id", referencedColumnName="id"))
//    private Set<Course> courses = new HashSet<>();

    @OneToMany
    @JoinColumn(name = "added_courses")
    private Set<AddedCourse> addedCourses = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "trimester")
    private Trimester trimester;

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

//    @JsonIgnore
//    public Set<Course> getCourses() {
//        return courses;
//    }
//
//    public void setCourses(Set<Course> courses) {
//        this.courses = courses;
//    }

//    @JsonIgnore
    public Set<AddedCourse> getAddedCourses() {
        return addedCourses;
    }

    public void setAddedCourses(Set<AddedCourse> addedCourses) {
        this.addedCourses = addedCourses;
    }

    @JsonIgnore
    public Trimester getTrimester() {
        return trimester;
    }

    public void setTrimester(Trimester trimester) {
        this.trimester = trimester;
    }
}
