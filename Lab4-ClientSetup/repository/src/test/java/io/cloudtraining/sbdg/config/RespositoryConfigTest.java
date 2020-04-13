package io.cloudtraining.sbdg.config;

import io.cloudtraining.sbdg.model.Person;
import io.cloudtraining.sbdg.repo.PersonRepository;
import org.apache.geode.cache.GemFireCache;
import org.apache.geode.cache.Region;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class RespositoryConfigTest {

    /**
     * We really need to make sure that everything is wired up correctly since we are relying on the Framework
     * to pull all this in together for us.
     *
     * Normally I wouldn't do this but it is great for demonstration purposes.
     *
     * Keep in mind that SBDG will create the regions and cache for us, in certain cases it will create these
     * internally instead if using the cache and config from PCC. This is great for internal testing much like
     * H2 embedded databases. Thus we are just testing that the annotations in the SpringBoot config are in place.
     *
     * ** NOTE - This can be misleading. Just because the cache exists don't assume that it is external to the
     * application.
     */

    @Autowired
    private GemFireCache cache;

    @Autowired
    @Qualifier("person")
    private Region<String, Person> personRegion;

    @Autowired
    private PersonRepository personRepository;

    /**
     * Please note that I trained with Pivotal and the idea of this test class came specifically from the work I did
     * with Jeff Cherng.
     *
     * @see <a href="https://github.com/jcherng-pivotal/pcc-workshop"</a>
     *
     * Other examples are derived from Pivotal Documentation. Once such example is Pivotal's SBDG Pizza Shop example.
     * The Pizza Shop example is approximately 2 years old and MUCH HAS CHANGED in the framework since then. As such
     * it provided a very minimal blueprint that should not be followed line for line. THe latest starters are API releases
     * have eliminated the need to do much of the config and annotation specific wiring that is detailed in the older
     * samples
     *
     * @see <a href="https://github.com/pivotal-cf/PCC-Sample-App-PizzaStore"</a>
     */
    @Test
    void contextLoads() {
        assertThat(cache).isNotNull();
        assertThat(personRegion).isNotNull();
        assertThat(personRepository).isNotNull();
    }

}

