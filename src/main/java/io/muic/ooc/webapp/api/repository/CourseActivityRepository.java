package io.muic.ooc.webapp.api.repository;

import io.muic.ooc.webapp.api.entity.CourseActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by tyeon on 3/11/17.
 */
@RepositoryRestResource(exported = false)
public interface CourseActivityRepository extends JpaRepository<CourseActivity, Long> {
}
