package com.bamboo.common.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

@ConditionalOnProperty(prefix = "bamboo", name = "spring-session-open", havingValue = "true")
public class SpringSessionConfig {

}
