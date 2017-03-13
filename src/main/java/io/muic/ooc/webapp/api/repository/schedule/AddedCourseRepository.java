package io.muic.ooc.webapp.api.repository.schedule;

import io.muic.ooc.webapp.api.entity.schedule.AddedCourse;
import io.muic.ooc.webapp.api.entity.course.Course;
import io.muic.ooc.webapp.api.entity.schedule.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Set;

/**
 * Created by tyeon on 3/11/17.
 */
@RepositoryRestResource(exported = false)
public interface AddedCourseRepository extends JpaRepository<AddedCourse, Long> {
    Set<AddedCourse> findBySchedule(Schedule schedule);
    AddedCourse findByScheduleAndCourse(Schedule schedule, Course course);
}
