package io.muic.ooc.webapp.api.service;

import io.muic.ooc.webapp.api.ActivityLogger;
import io.muic.ooc.webapp.api.ActivityType;
import io.muic.ooc.webapp.api.entity.*;
import io.muic.ooc.webapp.api.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashSet;
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
    @Autowired
    private AddedCourseService addedCourseService;
    @Autowired
    private ScheduleActivityRepository scheduleActivityRepository;
    private ActivityLogger activityLogger;
    @PostConstruct
    private void setActivityLogger() {
        activityLogger = new ActivityLogger(ScheduleActivity.class, scheduleActivityRepository);
    }

    public long count() {
        return scheduleRepository.count();
    }

    public List<Schedule> findAll() {
        return scheduleRepository.findAll();
    }

    public Schedule findOne(long id) {
        return scheduleRepository.findOne(id);
    }

    public Schedule findByUserAndTrimester(long userId, long trimesterId) {
        User user = userRepository.findOne(userId);
        Trimester trimester = trimesterRepository.findOne(trimesterId);
        return scheduleRepository.findByUserAndTrimester(user, trimester);
    }

    public User getUser(long id) {
        Schedule schedule = findOne(id);
        return schedule.getUser();
    }

    public Trimester getTrimester(long id) {
        Schedule schedule = findOne(id);
        return schedule.getTrimester();
    }

    public Set<Course> getCourses(long id) {
        Schedule schedule = findOne(id);
        Set<AddedCourse> addedCourses = addedCourseService.findBySchedule(schedule);
        Set<Course> courses = new HashSet<>();
        for (AddedCourse addedCourse : addedCourses) {
            courses.add(addedCourse.getCourse());
        }
        return courses;
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
            activityLogger.log(ActivityType.ADDED.toString(), schedule.getId(), userId, schedule.toString(), new Date());
        }
        return schedule;
    }

    public Schedule addOrUpdateCourse(long id, Long courseId, String type, String reason) {
        Schedule schedule = scheduleRepository.findOne(id);
        Course course = courseRepository.findOne(courseId);
        if (schedule != null && course != null) {
            AddedCourse addedCourse = addedCourseService.findByScheduleAndCourse(id, courseId);
            if (addedCourse != null) addedCourseService.update(addedCourse.getId(), type, reason);
            else {
                addedCourse = addedCourseService.create(type, reason, schedule.getId(), course.getId());
                Set<AddedCourse> addedCourses = schedule.getAddedCourses();
                addedCourses.add(addedCourse);
                schedule.setAddedCourses(addedCourses);
                schedule = save(schedule);
            }
            activityLogger.log(ActivityType.UPDATED.toString(), schedule.getId(), addedCourse.getId(), schedule.toString(), new Date());
        }
        return schedule;
    }

    public Schedule removeCourse(long id, long addedCourseId) {
        Schedule schedule = scheduleRepository.findOne(id);
        if (schedule != null) {
            Set<AddedCourse> addedCourses = schedule.getAddedCourses();
            for (AddedCourse addedCourse : addedCourses) {
                if (addedCourse.getId() == addedCourseId) {
                    addedCourses.remove(addedCourse);
                    break;
                }
            }
            schedule.setAddedCourses(addedCourses);
            schedule = save(schedule);
            activityLogger.log(ActivityType.UPDATED.toString(), schedule.getId(), addedCourseId, schedule.toString(), new Date());
            addedCourseService.archive(addedCourseId);
        }
        return schedule;
    }

    public void archive(long id) {
        Schedule schedule = findOne(id);
        if (schedule != null) {
            schedule.setActive(false);
            save(schedule);
            activityLogger.log(ActivityType.REMOVED.toString(), schedule.getId(), schedule.getUser().getId(), schedule.toString(), new Date());
        }
    }
}
