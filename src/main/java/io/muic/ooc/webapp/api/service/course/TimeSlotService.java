package io.muic.ooc.webapp.api.service.course;

import io.muic.ooc.webapp.api.entity.course.Course;
import io.muic.ooc.webapp.api.entity.course.Slot;
import io.muic.ooc.webapp.api.entity.course.TimeSlot;
import io.muic.ooc.webapp.api.repository.course.SlotRepository;
import io.muic.ooc.webapp.api.repository.course.TimeSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by tyeon on 3/13/17.
 */
@Transactional
@Service
public class TimeSlotService {
    @Autowired
    private TimeSlotRepository timeSlotRepository;
    @Autowired
    private SlotRepository slotRepository;

    public long count() {
        return timeSlotRepository.count();
    }

    public List<TimeSlot> findAll() {
        return timeSlotRepository.findAll();
    }

    public TimeSlot findOne(long id) {
        return timeSlotRepository.findOne(id);
    }

    private TimeSlot save(TimeSlot timeSlot) {
        return timeSlotRepository.save(timeSlot);
    }

    public TimeSlot create(Course course, List<Long> slotIds) {
        TimeSlot timeSlot = new TimeSlot();
        timeSlot.setCourse(course);
        Set<Slot> slots = new HashSet<>();
        for (Long id : slotIds) {
            Slot slot = slotRepository.findOne(id);
            if (id != null && slot != null) slots.add(slot);
        }
        timeSlot.setSlots(slots);
        timeSlot = save(timeSlot);
        return timeSlot;
    }

    public TimeSlot update(List<Long> slotIds) {
        TimeSlot timeSlot = new TimeSlot();
        Set<Slot> slots = new HashSet<>();
        for (Long id : slotIds) {
            Slot slot = slotRepository.findOne(id);
            if (id != null && slot != null) slots.add(slot);
        }
        timeSlot.setSlots(slots);
        timeSlot = save(timeSlot);
        return timeSlot;
    }

    public void archive(long id) {
        TimeSlot timeSlot = findOne(id);
        if (timeSlot != null) {
            timeSlot.setActive(false);
            save(timeSlot);
        }
    }
}
