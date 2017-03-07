package io.muic.ooc.webapp.api.repository;


import io.muic.ooc.webapp.api.entity.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserGroupRepository extends JpaRepository<UserGroup, Long> {

}
