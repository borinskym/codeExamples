package dynamicPropertyInjectionSpring;

import config.MongoConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.util.Properties;

@Configuration
@ComponentScan(basePackages = {"config", "pebble"},
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = MongoConfig.class),
        })
public class DynamicPropsConfig {
    static String EVENT_SERVICE_URL ="localhost:8083";
    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() throws Exception {
        final PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
        pspc.setProperties(createProperties());
        return pspc;
    }

    private static Properties createProperties() {
        Properties properties = new Properties();
        properties.setProperty("event-service.url", EVENT_SERVICE_URL);
        return properties;
    }

}
