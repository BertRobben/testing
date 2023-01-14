package bert.test;

import bert.spring.BertJUnitConfig;
import bert.spring.ConfigInitializer;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigValueFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

@BertJUnitConfig(configInitializers = { ConfigInitializerTest.MyConfigInitializer.class })
public class ConfigInitializerTest {

    @Autowired
    private Config config;

    @Test
    void shouldUpdateConfig() {
        assertEquals(10, config.getInt("updated"));
    }
    public static class MyConfigInitializer implements ConfigInitializer {

        @Override
        public Config apply(Config config) {
            return config.withValue("updated", ConfigValueFactory.fromAnyRef(10));
        }
    }
}
