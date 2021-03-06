package io.muic.ooc.webapp.api.repository.course;

import io.muic.ooc.webapp.api.entity.course.CourseActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Set;

/**
 * Created by tyeon on 3/11/17.
 */
@RepositoryRestResource(exported = false)
public interface CourseActivityRepository extends JpaRepository<CourseActivity, Long> {
    Set<CourseActivity> findTop20ByOrderByIdDesc();
}
