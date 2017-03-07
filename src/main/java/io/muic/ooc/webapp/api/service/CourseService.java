package io.muic.ooc.webapp.api.service;

import io.muic.ooc.webapp.api.entity.Course;
import io.muic.ooc.webapp.api.entity.Schedule;
import io.muic.ooc.webapp.api.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    public long count() {
        return courseRepository.count();
    }

    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public Course findOne(long id) {
        return courseRepository.findOne(id);
    }

    public Set<Course> findBySchedule(Schedule schedule) {
        return courseRepository.findBySchedule(schedule);
    }

    private Course save(Course schedule) {
        return courseRepository.save(schedule);
    }

    public Course create(String code, String name, String time, int capacity) {
        Course course = new Course();
        course.setCode(code);
        course.setName(name);
        course.setTime(time);
        course.setCapacity(capacity);
        return save(course);
    }

    public Course update(long id, String code, String name, String time, int capacity) {
        Course course = findOne(id);
        if (course != null) {
            course.setCode(code);
            course.setName(name);
            course.setTime(time);
            course.setCapacity(capacity);
            course = save(course);
        }
        return course;
    }

    public void archive(long id) {
        Course course = findOne(id);
        course.setActive(false);
        save(course);
    }
}