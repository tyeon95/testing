package io.muic.ooc.webapp.api.service.schedule;

import io.muic.ooc.webapp.api.ActivityLogger;
import io.muic.ooc.webapp.api.ActivityType;
import io.muic.ooc.webapp.api.entity.course.Course;
import io.muic.ooc.webapp.api.entity.schedule.AddedCourse;
import io.muic.ooc.webapp.api.entity.schedule.AddedCourseActivity;
import io.muic.ooc.webapp.api.entity.schedule.Schedule;
import io.muic.ooc.webapp.api.repository.course.CourseRepository;
import io.muic.ooc.webapp.api.repository.schedule.AddedCourseActivityRepository;
import io.muic.ooc.webapp.api.repository.schedule.AddedCourseRepository;
import io.muic.ooc.webapp.api.repository.schedule.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by tyeon on 3/11/17.
 */
@Transactional
@Service
public class AddedCourseService {
    @Autowired
    private AddedCourseRepository addedCourseRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private AddedCourseActivityRepository addedCourseActivityRepository;
    private ActivityLogger activityLogger;
    @PostConstruct
    private void setActivityLogger() {
        activityLogger = new ActivityLogger(AddedCourseActivity.class, addedCourseActivityRepository);
    }

    public long count() {
        return addedCourseRepository.count();
    }

    public List<AddedCourse> findAll() {
        return addedCourseRepository.findAll();
    }

    public AddedCourse findOne(long id) {
        return addedCourseRepository.findOne(id);
    }

    public Set<AddedCourse> findBySchedule(Schedule schedule) {
        return addedCourseRepository.findBySchedule(schedule);
    }

    public AddedCourse findByScheduleAndCourse(Long scheduleId, Long courseId) {
        Schedule schedule = scheduleRepository.findOne(scheduleId);
        Course course = courseRepository.findOne(courseId);
        return addedCourseRepository.findByScheduleAndCourse(schedule, course);
    }

    private AddedCourse save(AddedCourse addedCourse) {
        return addedCourseRepository.save(addedCourse);
    }

    public AddedCourse create(String type, String reason, Long scheduleId, Long courseId) {
        Schedule schedule = scheduleRepository.findOne(scheduleId);
        Course course = courseRepository.findOne(courseId);
        AddedCourse addedCourse = new AddedCourse();
        if (schedule != null && course != null) {
            addedCourse.setType(AddedCourse.AddType.valueOf(type));
            addedCourse.setReason(AddedCourse.AddReason.valueOf(reason));
            addedCourse.setSchedule(schedule);
            addedCourse.setCourse(course);
            addedCourse = save(addedCourse);
            activityLogger.log(ActivityType.ADDED.toString(), schedule.getId(), course.getId(), addedCourse.toString(), new Date());
        }
        return addedCourse;
    }

    public AddedCourse update(long id, String type, String reason) {
        AddedCourse addedCourse = findOne(id);
        if (addedCourse != null) {
            addedCourse.setType(AddedCourse.AddType.valueOf(type));
            addedCourse.setReason(AddedCourse.AddReason.valueOf(reason));
            addedCourse = save(addedCourse);
            activityLogger.log(ActivityType.UPDATED.toString(), addedCourse.getSchedule().getId(), addedCourse.getCourse().getId(),
                    addedCourse.toString(), new Date());
        }
        return addedCourse;
    }

    public void archive(long id) {
        AddedCourse addedCourse = findOne(id);
        if (addedCourse != null) {
            addedCourse.setActive(false);
            addedCourse = save(addedCourse);
            activityLogger.log(ActivityType.REMOVED.toString(), addedCourse.getSchedule().getId(), addedCourse.getCourse().getId(),
                    addedCourse.toString(), new Date());
        }
    }
}
