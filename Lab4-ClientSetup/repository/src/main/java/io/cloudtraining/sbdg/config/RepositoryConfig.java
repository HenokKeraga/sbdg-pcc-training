package io.cloudtraining.sbdg.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.gemfire.config.annotation.EnableEntityDefinedRegions;
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories;
import org.springframework.geode.config.annotation.EnableClusterAware;

@Configuration
@EnableEntityDefinedRegions(basePackages = "io.cloudtraining.sbdg.model")
@EnableGemfireRepositories(basePackages = "io.cloudtraining.sbdg.repo")
@EnableClusterAware
public class RepositoryConfig {
}
