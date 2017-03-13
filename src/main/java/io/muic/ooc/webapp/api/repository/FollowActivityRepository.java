package io.muic.ooc.webapp.api.repository;

import io.muic.ooc.webapp.api.entity.FollowActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by tyeon on 3/13/17.
 */
@RepositoryRestResource(exported = false)
public interface FollowActivityRepository extends JpaRepository<FollowActivity, Long> {
}
