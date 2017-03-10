package io.muic.ooc.webapp.api.service;

import io.muic.ooc.webapp.api.entity.Course;
import io.muic.ooc.webapp.api.entity.Schedule;
import io.muic.ooc.webapp.api.entity.Trimester;
import io.muic.ooc.webapp.api.entity.User;
import io.muic.ooc.webapp.api.repository.CourseRepository;
import io.muic.ooc.webapp.api.repository.ScheduleRepository;
import io.muic.ooc.webapp.api.repository.TrimesterRepository;
import io.muic.ooc.webapp.api.repository.UserRepository;
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
public class ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private TrimesterRepository trimesterRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private UserRepository userRepository;

    public long count() {
        return scheduleRepository.count();
    }

    public List<Schedule> findAll() {
        return scheduleRepository.findAll();
    }

    public Schedule findOne(long id) {
        return scheduleRepository.findOne(id);
    }

    public Set<Schedule> findByUser(User user) {
        return scheduleRepository.findByUser(user);
    }

    private Schedule save(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public Schedule create(Long userId, Long trimesterId) {
        Schedule schedule = new Schedule();
        User user = userRepository.findOne(userId);
        Trimester trimester = trimesterRepository.findOne(trimesterId);
        if (user != null && trimester != null) {
            schedule.setUser(user);
            schedule.setTrimester(trimester);
            schedule = save(schedule);
        }
        return schedule;
    }

    public Schedule addCourse(long id, Long courseId) {
        Schedule schedule = scheduleRepository.findOne(id);
        if (schedule != null && courseId != null) {
            Course course = courseRepository.findOne(courseId);
            if (course != null) {
                Set<Course> courses = schedule.getCourses();
                courses.add(course);
            }
            schedule = save(schedule);
        }
        return schedule;
    }

    public Schedule removeCourse(long id, Long courseId) {
        Schedule schedule = scheduleRepository.findOne(id);
        if (schedule != null && courseId != null) {
            Set<Course> courses = schedule.getCourses();
            for (Course course : courses) {
                if (course.getId() == courseId) {
                    courses.remove(course);
                    break;
                }
            }
            schedule = save(schedule);
        }
        return schedule;
    }

    public void archive(long id) {
        Schedule schedule = findOne(id);
        schedule.setActive(false);
        save(schedule);
    }
}
