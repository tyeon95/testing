package io.muic.ooc.webapp.api.repository;

import io.muic.ooc.webapp.api.entity.Trimester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Set;

/**
 * Created by tyeon on 3/7/17.
 */
@RepositoryRestResource(exported = false)
public interface TrimesterRepository extends JpaRepository<Trimester, Long> {
    Set<Trimester> findByYearOrderByIdAsc(int year);
    Trimester findByTrimesterAndYear(int trimester, int year);
    Trimester findTopByOrderByYearDescTrimesterDesc();
}
