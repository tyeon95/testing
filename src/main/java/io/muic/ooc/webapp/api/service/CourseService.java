package io.muic.ooc.webapp.api.service;

import io.muic.ooc.webapp.api.ActivityLogger;
import io.muic.ooc.webapp.api.entity.Course;
import io.muic.ooc.webapp.api.entity.CourseActivity;
import io.muic.ooc.webapp.api.entity.Schedule;
import io.muic.ooc.webapp.api.entity.Trimester;
import io.muic.ooc.webapp.api.repository.CourseActivityRepository;
import io.muic.ooc.webapp.api.repository.CourseRepository;
import io.muic.ooc.webapp.api.repository.TrimesterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.HashSet;
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

    public Course create(String code, String name, String time, int capacity, Long trimesterId) {
        Course course = new Course();
        course.setCode(code);
        course.setName(name);
        course.setTime(time);
        course.setCapacity(capacity);
        setTrimesters(course, trimesterId);
        return save(course);
    }

    public Course update(long id, String code, String name, String time, int capacity, Long trimesterId) {
        Course course = findOne(id);
        if (course != null) {
            course.setCode(code);
            course.setName(name);
            course.setTime(time);
            course.setCapacity(capacity);
            setTrimesters(course, trimesterId);
            course = save(course);
        }
        return course;
    }

    public void archive(long id) {
        Course course = findOne(id);
        if (course != null) {
            course.setActive(false);
            save(course);
        }
    }
}