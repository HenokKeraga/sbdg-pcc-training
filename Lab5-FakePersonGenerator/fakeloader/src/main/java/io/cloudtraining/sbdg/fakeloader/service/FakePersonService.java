package io.cloudtraining.sbdg.fakeloader.service;


import io.cloudtraining.sbdg.model.Person;
import io.cloudtraining.sbdg.repo.PersonRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Slf4j
@AllArgsConstructor
@Service
public class FakePersonService {
    private FakePersonGenerator fakePersonGenerator;

    private final PersonRepository personRepository;

    @Autowired
    public FakePersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
        fakePersonGenerator = new FakePersonGenerator();
    }

    public void loadFakePeople(int numberOfPeople) {
        Set<Person> people = fakePersonGenerator.generateFakePersonRecords(numberOfPeople);
        personRepository.saveAll(people);
    }

}
