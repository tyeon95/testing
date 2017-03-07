package io.muic.ooc.webapp.api.service;

import io.muic.ooc.webapp.api.entity.Course;
import io.muic.ooc.webapp.api.entity.Schedule;
import io.muic.ooc.webapp.api.entity.User;
import io.muic.ooc.webapp.api.repository.CourseRepository;
import io.muic.ooc.webapp.api.repository.ScheduleRepository;
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

    public Schedule create(Long userId) {
        Schedule schedule = new Schedule();
        User user = userRepository.findOne(userId);
        if (user != null) {
            schedule.setUser(user);
            schedule = save(schedule);
        }
        return schedule;
    }

    public Schedule update(long id) {
        Schedule schedule = findOne(id);
        if (schedule != null) {
            schedule = save(schedule);
        }
        return schedule;
    }

    private void archiveRelations(Schedule schedule) {
        for (Course course : courseRepository.findBySchedule(schedule)) {
            course.setActive(false);
            courseRepository.save(course);
        }
    }

    public void archive(long id) {
        Schedule schedule = findOne(id);
        archiveRelations(schedule);
        schedule.setActive(false);
        save(schedule);
    }
}
