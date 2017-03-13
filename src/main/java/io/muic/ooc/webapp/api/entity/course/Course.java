package io.muic.ooc.webapp.api.entity.course;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.muic.ooc.webapp.api.entity.Trimester;
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
    @Transient
    public static final String SINGULAR = "course";
    @Transient
    public static final String PLURAL = SINGULAR + "s";

    @Id
    @GeneratedValue
    private long id;

    private String code;

    @Type(type="text")
    private String name;

    private String time; /* TODO: change timeslot to: Start and End Dates */

    private int capacity;

    @ManyToMany
    @JoinTable(name="join_course_trimester",
            joinColumns=@JoinColumn(name="course_id", referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(name="trimester_id", referencedColumnName="id"))
    private Set<Trimester> trimesters = new HashSet<>();

//    @ManyToMany(cascade= CascadeType.ALL, mappedBy="courses")
//    @OrderBy(value="created")
//    private Set<Schedule> schedules = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "timeslot")
    private TimeSlot timeSlot;

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

//    @JsonIgnore
//    public Set<Schedule> getSchedules() {
//        return schedules;
//    }
//
//    public void setSchedules(Set<Schedule> schedules) {
//        this.schedules = schedules;
//    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }

    @Override
    public String toString() {
        return String.format(
            "Course[id=%d, code='%s', name='%s', time='%s', capacity=%d]",
            id, code, name, time, capacity);
    }
}
