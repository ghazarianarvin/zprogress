package com.zprogress.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.UriTemplate;
import org.springframework.hateoas.mediatype.hal.CurieProvider;
import org.springframework.hateoas.mediatype.hal.DefaultCurieProvider;
import org.springframework.hateoas.mediatype.hal.forms.HalFormsConfiguration;

@Configuration
public class HateoasConfiguration {

    @Bean
    public CurieProvider defaultCurieProvider() {
        return new DefaultCurieProvider("zprogress", UriTemplate.of("/doc/{rel}"));
    }

    @Bean
    public HalFormsConfiguration halFormsConfiguration() {
        return new HalFormsConfiguration();
    }
}
