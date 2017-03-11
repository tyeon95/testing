package io.muic.ooc.webapp.api.repository;

import io.muic.ooc.webapp.api.entity.AddedCourse;
import io.muic.ooc.webapp.api.entity.Course;
import io.muic.ooc.webapp.api.entity.Schedule;
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
