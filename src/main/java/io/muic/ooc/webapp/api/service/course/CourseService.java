package io.muic.ooc.webapp.api.service.course;

import io.muic.ooc.webapp.api.ActivityLogger;
import io.muic.ooc.webapp.api.ActivityType;
import io.muic.ooc.webapp.api.entity.*;
import io.muic.ooc.webapp.api.entity.course.Course;
import io.muic.ooc.webapp.api.entity.course.CourseActivity;
import io.muic.ooc.webapp.api.entity.course.TimeSlot;
import io.muic.ooc.webapp.api.repository.course.CourseActivityRepository;
import io.muic.ooc.webapp.api.repository.course.CourseRepository;
import io.muic.ooc.webapp.api.repository.TrimesterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by tyeon on 3/7/17.
 */

@Transactional
@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private TrimesterRepository trimesterRepository;
    @Autowired
    private TimeSlotService timeSlotService;
    @Autowired
    private CourseActivityRepository courseActivityRepository;
    private ActivityLogger activityLogger;
    @PostConstruct
    private void setActivityLogger() {
        activityLogger = new ActivityLogger(CourseActivity.class, courseActivityRepository);
    }

    public long count() {
        return courseRepository.count();
    }

    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public Course findOne(long id) {
        return courseRepository.findOne(id);
    }

    private Course save(Course course) {
        return courseRepository.save(course);
    }

    private Course setTrimesters(Course course, Long trimesterId) {
        if (trimesterId != null) {
            Trimester trimester = trimesterRepository.findOne(trimesterId);
            if (trimester != null) {
                Set<Trimester> trimesters = course.getTrimesters();
                trimesters.add(trimester);
                course.setTrimesters(trimesters);
            }
        }
        return course;
    }

    public Course create(String code, String name, String time, int capacity, Long trimesterId, List<Long> slotIds) {
        Course course = new Course();
        course.setCode(code);
        course.setName(name);
        course.setTime(time);
        course.setCapacity(capacity);
        setTrimesters(course, trimesterId);
        course = save(course);
        TimeSlot timeSlot = timeSlotService.create(course, slotIds);
        course.setTimeSlot(timeSlot);
        course = save(course);
        activityLogger.log(ActivityType.ADDED.toString(), course.getId(), trimesterId, course.toString(), new Date());
        return course;
    }

    public Course update(long id, String code, String name, String time, int capacity, Long trimesterId, List<Long> slotIds) {
        Course course = findOne(id);
        if (course != null) {
            course.setCode(code);
            course.setName(name);
            course.setTime(time);
            course.setCapacity(capacity);
            setTrimesters(course, trimesterId);
            TimeSlot timeSlot = timeSlotService.update(slotIds);
            course.setTimeSlot(timeSlot);
            course = save(course);
            activityLogger.log(ActivityType.UPDATED.toString(), course.getId(), trimesterId, course.toString(), new Date());
        }
        return course;
    }

    private void nullifyRelations(Course course) {
        TimeSlot timeSlot = course.getTimeSlot();
        timeSlot.setActive(false);
        timeSlotService.archive(timeSlot.getId());
    }

    public void archive(long id) {
        Course course = findOne(id);
        if (course != null) {
            course.setActive(false);
            nullifyRelations(course);
            course = save(course);
            activityLogger.log(ActivityType.REMOVED.toString(), course.getId(), 0L, course.toString(), new Date());
        }
    }
}