package io.muic.ooc.webapp.api.repository;

import io.muic.ooc.webapp.api.entity.Schedule;
import io.muic.ooc.webapp.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Set;

/**
 * Created by tyeon on 3/7/17.
 */

@RepositoryRestResource(exported = false)
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    Set<Schedule> findByUser(User user);
}
