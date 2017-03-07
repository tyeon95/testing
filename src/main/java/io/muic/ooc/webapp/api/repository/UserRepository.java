package io.muic.ooc.webapp.api.repository;

import java.util.Set;

import io.muic.ooc.webapp.api.entity.User;
import io.muic.ooc.webapp.api.entity.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface UserRepository extends JpaRepository<User, Long> {

    Set<User> findByUserGroup(UserGroup userGroup);

    Set<User> findByUserGroupOrderByCreatedDesc(UserGroup userGroup);
}
