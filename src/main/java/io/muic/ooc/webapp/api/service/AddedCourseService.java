package io.muic.ooc.webapp.api.service;

import io.muic.ooc.webapp.api.entity.AddedCourse;
import io.muic.ooc.webapp.api.entity.Course;
import io.muic.ooc.webapp.api.entity.Schedule;
import io.muic.ooc.webapp.api.entity.Trimester;
import io.muic.ooc.webapp.api.repository.AddedCourseRepository;
import io.muic.ooc.webapp.api.repository.CourseRepository;
import io.muic.ooc.webapp.api.repository.ScheduleRepository;
import io.muic.ooc.webapp.api.repository.TrimesterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
        }
        return addedCourse;
    }

    public AddedCourse update(long id, String type, String reason) {
        AddedCourse addedCourse = findOne(id);
        if (addedCourse != null) {
            addedCourse.setType(AddedCourse.AddType.valueOf(type));
            addedCourse.setReason(AddedCourse.AddReason.valueOf(reason));
            addedCourse = save(addedCourse);
        }
        return addedCourse;
    }

    public void archive(long id) {
        AddedCourse addedCourse = findOne(id);
        addedCourse.setActive(false);
        save(addedCourse);
    }
}
