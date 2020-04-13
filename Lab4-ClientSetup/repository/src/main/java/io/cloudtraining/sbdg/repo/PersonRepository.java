package io.cloudtraining.sbdg.repo;

import io.cloudtraining.sbdg.model.Person;
import org.springframework.data.gemfire.repository.GemfireRepository;
import org.springframework.data.gemfire.repository.query.annotation.Trace;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Pivotal GemFire specific extension of the Spring Data {@link CrudRepository} interface.
 *
 * @see org.springframework.data.repository.CrudRepository
 */
public interface PersonRepository extends GemfireRepository<Person, String> {
    @Trace
    List<Person> findByLastName(String lastName);
}