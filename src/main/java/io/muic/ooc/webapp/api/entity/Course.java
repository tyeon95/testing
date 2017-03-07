package io.muic.ooc.webapp.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.muic.ooc.webapp.api.entity.auditing.BaseEntity;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by tyeon on 3/7/17.
 */
@Entity
@Where(clause = "is_active = 1")
public class Course extends BaseEntity {
    @Id
    @GeneratedValue
    private long id;

    private String code;

    @Type(type="text")
    private String name;

    private String time; /* TODO: change timeslot to: Start and End Dates */

    private int capacity;

    @ManyToMany(cascade= CascadeType.ALL, mappedBy="courses")
    @OrderBy(value="created")
    private Set<Trimester> trimesters = new HashSet<>();

    public long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @JsonIgnore
    public Set<Trimester> getTrimesters() {
        return trimesters;
    }

    public void setTrimesters(Set<Trimester> trimesters) {
        this.trimesters = trimesters;
    }
}
