package com.crazy.coding.productservice.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties

public class ConfigProperties {

    @Value("${test.name}")
    private String name;
}
