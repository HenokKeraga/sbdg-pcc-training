package io.cloudtraining.sbdg;

import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @SpringBootTest requires that there be a SpringBoot Application in order to bootstrap the test
 *
 * I only do this here because the Repository Module doesn't have a SpringBoot Application in the
 * main Source Code module (It is a library module)
 *
 * Keep in mind that SpringBoot will automatically do a Classpath Scan of all classes from the base
 * of the package here down.
 */

@SpringBootApplication
class RepositoryTestApp {

}
