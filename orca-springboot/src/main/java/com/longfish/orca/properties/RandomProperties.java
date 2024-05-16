package com.longfish.orca.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "random")
public class RandomProperties {

    private Integer intSeed;

    private String seed;
}
