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

    /**
     * I include this here just for testing using POST. Consideration must be made on whether
     * this is a valid POST method. In reality it is a batch operation and this doesn't lend itself
     * REST where you expect an operation on a resource. To be sure, a resource can be a collection.
     *
     * In general, API designers should choose standard methods over custom methods whenever feasible.
     * A BATCH OPERATION is not easily represented here. We could create a custom method which is a
     * bit overkill and NON standard. (Plus doesn't lend itself to built in Spring functionality)
     *
     * Please refer to the discussion here:
     *
     * @see <a href="https://cloud.google.com/apis/design/custom_methods"</a>
     */
    @PostMapping("/emptyRepo")
    @ApiOperation(value = "Deletes the whole Database")
    @ResponseStatus(NO_CONTENT)
    public void emptyPersonRepo(final HttpServletResponse response) {
        /**
         * Generally NO_CONTENT (HTTP Status 204 is cacheable by default)
         * I wish to override this on my put operation so I will do so with a response header
         */
        response.addHeader("Cache-Control","no-cache, no-store, max-age=0, must-revalidate");
        this.personRepository.deleteAll();
    }

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

