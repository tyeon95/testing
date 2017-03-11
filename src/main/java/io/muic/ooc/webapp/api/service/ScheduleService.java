package io.muic.ooc.webapp.api.service;

import io.muic.ooc.webapp.api.entity.*;
import io.muic.ooc.webapp.api.repository.*;
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
    @Autowired
    private AddedCourseService addedCourseService;

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

    public Set<AddedCourse> getCourses(long id) {
        Schedule schedule = findOne(id);
        return addedCourseService.findBySchedule(schedule);
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
        }
        return schedule;
    }

    public Schedule removeCourse(long id, Long addedCourseId) {
        Schedule schedule = scheduleRepository.findOne(id);
        if (schedule != null && addedCourseId != null) {
            Set<AddedCourse> addedCourses = schedule.getAddedCourses();
            for (AddedCourse addedCourse : addedCourses) {
                if (addedCourse.getId() == addedCourseId) {
                    addedCourses.remove(addedCourse);
                    break;
                }
            }
            schedule.setAddedCourses(addedCourses);
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
