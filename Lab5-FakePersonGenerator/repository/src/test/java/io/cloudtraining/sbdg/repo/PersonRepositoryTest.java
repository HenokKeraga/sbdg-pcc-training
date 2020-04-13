package io.cloudtraining.sbdg.repo;

import io.cloudtraining.sbdg.model.Person;
import org.apache.geode.cache.GemFireCache;
import org.apache.geode.cache.Region;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    @Qualifier("person")
    private Region<String, Person> personRegion;

    private Person person;

    @BeforeEach
    void setUp() {
        person = Person
                .newBuilder()
                .id("1234")
                .dateOfBirth(LocalDate.parse("2000-02-25"))
                .firstName("John")
                .lastName("Doe")
                .nickName("Trip")
                .build();
    }

    @Test
    void testCreate() {
        Person personCreated = personRepository.save(person);
        assertThat(personCreated).isNotNull();
        assertThat(personCreated).isEqualToComparingFieldByField(person);
    }

    @Test
    void testRead() {
        Person personCreated = personRepository.save(person);
        assertThat(personCreated).isNotNull();
        assertThat(personCreated).isEqualToComparingFieldByField(person);

        Person personFound = personRepository.findById("1234").get();
        assertThat(personFound).isNotNull();
        assertThat(personFound).isEqualToComparingFieldByField(person);
    }

    @Test
    void testFindByLastName() {
        Person personCreated = personRepository.save(person);
        assertThat(personCreated).isNotNull();
        assertThat(personCreated).isEqualToComparingFieldByField(person);

        List<Person> people = personRepository.findByLastName("Doe");

        assertThat(1).isEqualTo(people.size());

        assertThat(people.get(0)).isEqualToComparingFieldByField(person);
    }

    @Test
    void testUpdate() {
        assertThat(personRegion.isEmpty()).isTrue();
        Person personCreated = personRepository.save(person);
        assertThat(personRegion.size()).isEqualTo(1);

        assertThat(personCreated).isNotNull();
        assertThat(personCreated).isEqualToComparingFieldByField(person);

        personCreated.setNickName("John Boy");
        personRepository.save(personCreated);
        Person persistedPerson = personRepository.findById("1234").get();
        assertThat(personCreated.getNickName()).isEqualTo("John Boy");
    }

    @Test
    void testDelete() {
        Person personCreated = personRepository.save(person);
        assertThat(personCreated).isNotNull();
        assertThat(personRepository.findById("1234").get()).isEqualToComparingFieldByField(person);
        assertThat(personRegion.size()).isEqualTo(1);

        personRepository.deleteById("1234");

        assertThat(personRepository.findById("1234").isPresent()).isFalse();
        assertThat(personRegion.size()).isEqualTo(0);
    }

}

