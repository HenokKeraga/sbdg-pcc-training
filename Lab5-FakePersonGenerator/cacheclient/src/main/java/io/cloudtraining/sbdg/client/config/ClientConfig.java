package io.cloudtraining.sbdg.client.config;

import io.cloudtraining.sbdg.client.format.LocalDateFormatter;
import io.cloudtraining.sbdg.config.RepositoryConfig;
import org.springframework.context.annotation.*;
import org.springframework.format.Formatter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * This config class will be used to import the library classes and configuration from the
 * Repository Module. It will pull in all the transitive dependencies
 *
 * Alternative methods would include creating library jars out of the repository module,
 * versioning it with Semver or something similar and importing it as an external dependency.
 */

/**
 * @Import(RepositoryConfig.class)
 *
 * @Import Indicates one or more <em>component classes</em> to import &mdash; typically
 * {@link Configuration @Configuration} classes. In this case we are explicity linking to the
 * RepositoryConfig class.
 *
 * <p>Provides functionality equivalent to the {@code <import/>} element in Spring XML.
 * Allows for importing {@code @Configuration} classes, {@link ImportSelector} and
 * {@link ImportBeanDefinitionRegistrar} implementations, as well as regular component
 * classes (as of 4.2; analogous to {@link AnnotationConfigApplicationContext#register}).
 *
 * <p>{@code @Bean} definitions declared in imported {@code @Configuration} classes should be
 * accessed by using {@link org.springframework.beans.factory.annotation.Autowired @Autowired}
 * injection. Either the bean itself can be autowired, or the configuration class instance
 * declaring the bean can be autowired. The latter approach allows for explicit, IDE-friendly
 * navigation between {@code @Configuration} class methods
 */
@Configuration
@Import(RepositoryConfig.class)
public class ClientConfig {
    /**
     * NOTE - There is nothing to do here. The annotations alone will bootstrap the local embedded cache server
     */

    @Bean
    @Primary
    public Formatter<LocalDate> localDateFormatter() {
        return new LocalDateFormatter();
    }
}
