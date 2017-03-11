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
public class Trimester extends BaseEntity {
    @Transient
    public static final String SINGULAR = "trimester";
    @Transient
    public static final String PLURAL = SINGULAR + "s";

    @Id
    @GeneratedValue
    private long id;

    @ManyToMany(cascade= CascadeType.ALL, mappedBy="trimesters")
    @OrderBy(value="created")
    private Set<Course> courses = new HashSet<>();

    private int year;

    private int trimester;

    public long getId() {
        return id;
    }

    @JsonIgnore
    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getTrimester() {
        return trimester;
    }

    public void setTrimester(int trimester) {
        this.trimester = trimester;
    }
}
