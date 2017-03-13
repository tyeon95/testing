package io.muic.ooc.webapp.api.repository.course;

import io.muic.ooc.webapp.api.entity.course.Course;
import io.muic.ooc.webapp.api.entity.Trimester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Set;

/**
 * Created by tyeon on 3/7/17.
 */
@RepositoryRestResource(exported = false)
public interface CourseRepository extends JpaRepository<Course, Long> {
    Set<Course> findByTrimesters(Trimester trimester);
}
