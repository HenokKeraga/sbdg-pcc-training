package io.cloudtraining.sbdg.fakeloader.controller;

import io.cloudtraining.sbdg.fakeloader.service.FakePersonService;
import io.cloudtraining.sbdg.model.Person;
import io.cloudtraining.sbdg.repo.PersonRepository;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.geode.GemFireCheckedException;
import org.apache.geode.cache.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.gemfire.GemfireCallback;
import org.springframework.data.gemfire.GemfireTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/fakeloader")
public class FakeLoaderController {

    @Autowired
    private final FakePersonService fakePersonService;


    /**
     * Spring Automatically Auto Configures a GemfireTemplate for each Region that is created
     *
     * GemfireTemplate is a helper class that simplifies Pivotal GemFire data access code and converts {@link GemFireCheckedException} and
     * {@link GemFireException} into Spring {@link DataAccessException}, following the <tt>org.springframework.dao</tt>
     * exception hierarchy.
     */
    @Autowired
    @Qualifier("personTemplate")
    private GemfireTemplate personTemplate;


    @PostMapping("/loadFakePeople")
    @ResponseStatus(CREATED)
    public void loadFakePeople( @RequestParam(defaultValue = "100") int numberOfPeople) {
        fakePersonService.loadFakePeople(numberOfPeople);
    }

    /**
     * I include this here just for testing using DELETE. Consideration must be made on whether
     * this is a valid POST method. In reality it is a batch operation and this doesn't lend itself
     * REST where you expect an operation on a resource. To be sure, a resource can be a collection.
     *
     * In general, API designers should choose standard methods over custom methods whenever feasible.
     * A BATCH OPERATION is not easily represented here. We could create a custom method which is a
     * bit overkill and NON standard. (Plus doesn't lend itself to built in Spring functionality)
     *
     * I actually consider this a bulk operation as opposed to a batch one so I am going with DELETE
     *
     * Please refer to the discussion here:
     *
     * @see <a href="https://cloud.google.com/apis/design/custom_methods"</a>
     */
    @DeleteMapping("/emptyRepo")
    @ApiOperation(value = "Deletes the whole Database")
    @ResponseStatus(NO_CONTENT)
    public void emptyPersonRepo(final HttpServletResponse response) {

        /**
         * Generally NO_CONTENT (HTTP Status 204 is cacheable by default)
         * I wish to override this on my put operation so I will do so with a response header
         */

        response.addHeader("Cache-Control","no-cache, no-store, max-age=0, must-revalidate");
        deleteAll();
    }

    <K> void doRegionClear(Region<K, ?> region) {
        region.removeAll(region.keySetOnServer());
    }

    /**
     * Must clear region on the server side. We use a callback because all operations on a Persistent
     * region are done synchronously.
     */
    public void deleteAll() {
        this.personTemplate.execute((GemfireCallback<Void>) region -> {
            doRegionClear(region);
            return null;
        });
    }
}



