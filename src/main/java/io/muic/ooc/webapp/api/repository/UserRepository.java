package io.muic.ooc.webapp.api.repository;

import java.util.List;

import io.muic.ooc.webapp.api.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findByUsername(String username);
}
