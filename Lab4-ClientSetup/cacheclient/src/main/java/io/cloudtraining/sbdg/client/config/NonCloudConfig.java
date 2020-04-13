package io.cloudtraining.sbdg.client.config;

import org.apache.geode.cache.server.CacheServer;
import org.apache.geode.distributed.Locator;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.gemfire.config.annotation.CacheServerApplication;
import org.springframework.data.gemfire.config.annotation.EnableLocator;
import org.springframework.data.gemfire.config.annotation.EnableManager;

import java.lang.annotation.Annotation;

/**
 * This config will not be picked up by PCC as it automatically sets an active profile
 * of cloud.
 *
 * @CacheServerApplication -
 *  The CacheServerApplication annotation enables an embedded Pivotal GemFire {@link CacheServer} instance
 *  in a SDG-based application.
 *
 * @EnableLocator -
 * 	Configures the host/IP address on which the embedded {@link Locator} service will bind to
 * 	for accepting connections from clients sending {@link Locator} requests.
 *
 * @EnableManager -
 * The {@link EnableManager} annotation marks a Spring {@link Configuration @Configuration} annotated {@link Class}
 * to configure, embed and start a Pivotal GemFire/Apache Geode Manager service in this cluster member.
 *
 * Automatically sets {@literal jmx-manager} to {@literal true} just by specifying this {@link Annotation}
 * on your Spring application {@link Configuration @Configuration} annotated {@link Class}.
 *
 * @Profile("!cloud") -
 *  This is useful for local testing. We need to take care not to enable this on PCC, hence the use of @Profile
 *  to negate the class usage in cloud environments.
 */
@CacheServerApplication
@EnableLocator
@EnableManager
@Profile("!cloud")
public class NonCloudConfig {
}
