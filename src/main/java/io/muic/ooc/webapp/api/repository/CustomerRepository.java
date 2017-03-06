package io.muic.ooc.webapp.api.repository;

import java.util.List;

import io.muic.ooc.webapp.api.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByLastName(String lastName);
}
