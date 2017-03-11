package io.muic.ooc.webapp.api.repository;

import io.muic.ooc.webapp.api.entity.AddedCourseActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Set;

/**
 * Created by tyeon on 3/11/17.
 */
@RepositoryRestResource(exported = false)
public interface AddedCourseActivityRepository extends JpaRepository<AddedCourseActivity, Long> {
}
