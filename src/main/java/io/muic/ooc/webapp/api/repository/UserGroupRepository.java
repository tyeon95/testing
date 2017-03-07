package io.muic.ooc.webapp.api.repository;


import io.muic.ooc.webapp.api.entity.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface UserGroupRepository extends JpaRepository<UserGroup, Long> {
}
