package io.muic.ooc.webapp.api.entity.course;

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
public class TimeSlot extends BaseEntity {
    @Transient
    public static final String SINGULAR = "timeslot";
    @Transient
    public static final String PLURAL = SINGULAR + "s";

    @Id
    @GeneratedValue
    private long id;

    @OneToOne
    @JoinColumn(name = "course")
    private Course course;

    @OneToMany
    @JoinColumn(name = "slots")
    private Set<Slot> slots = new HashSet<>();

    public long getId() {
        return id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Set<Slot> getSlots() {
        return slots;
    }

    public void setSlots(Set<Slot> slots) {
        this.slots = slots;
    }
}
