package io.muic.ooc.webapp.api.repository.schedule;

import io.muic.ooc.webapp.api.entity.schedule.Schedule;
import io.muic.ooc.webapp.api.entity.Trimester;
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
    Set<Schedule> findByUserOrderByIdDesc(User user);
    Set<Schedule> findByTrimesterOrderByIdAsc(Trimester trimester);

    Schedule findByUserAndTrimester(User user, Trimester trimester);
}
