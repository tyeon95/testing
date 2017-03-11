package io.muic.ooc.webapp.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.muic.ooc.webapp.api.entity.auditing.BaseEntity;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by tyeon on 3/11/17.
 */
@Entity
@Where(clause = "is_active = 1")
public class AddedCourse extends BaseEntity {
    @Transient
    public static final String SINGULAR = "addedCourse";
    @Transient
    public static final String PLURAL = SINGULAR + "s";

    @Id
    @GeneratedValue
    private long id;

    private AddType type;

    private AddReason reason;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    public long getId() {
        return id;
    }

    public AddType getType() {
        return type;
    }

    public void setType(AddType type) {
        this.type = type;
    }

    public AddReason getReason() {
        return reason;
    }

    public void setReason(AddReason reason) {
        this.reason = reason;
    }

    @JsonIgnore
    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public enum AddType {
        WILL_TAKE,
        NEED_TO_TAKE,
        WANT_TO_TAKE
    }

    public enum AddReason {
        REQUIRED,
        CONFLICT_WITH_COURSE,
        CONFLICT_WITH_FRIEND
    }

    @Override
    public String toString() {
        return String.format(
            "Course[id=%d, schedule=%d, course=%d, type='%s', reason='%s']",
            id, schedule.getId(), course.getId(), type.toString(), reason.toString());
    }
}
