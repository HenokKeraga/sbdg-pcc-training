package io.cloudtraining.sbdg.client.controller;

import io.cloudtraining.sbdg.model.Person;
import io.cloudtraining.sbdg.repo.PersonRepository;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/persons")
public class DemoController {

    private final PersonRepository personRepository;


    @PostMapping("/create")
    @ResponseStatus(CREATED)
    public void create(@RequestBody Person person) {
        personRepository.save(person);
    }

    @GetMapping("/findById/{personId}")
    @ResponseStatus(OK)
    public Person read(@PathVariable("personId") String personId) {
        return personRepository.findById(personId).orElse(null);
    }

    @GetMapping("/findByLastName/{lastName}")
    @ResponseStatus(OK)
    public List<Person> findByLastName(@PathVariable("lastName") String lastName) {
        return personRepository.findByLastName(lastName);
    }

    @PutMapping("/update/{personId}")
    @ResponseStatus(OK)
    public Person update(@PathVariable("personId") String personId,
                           @RequestBody Person person) {
        return personRepository.save(person);
    }

    @DeleteMapping("/delete/{personId}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable("personId") String personId) {
        personRepository.deleteById(personId);
    }

}

