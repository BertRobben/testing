package bert.test.spring;

import bert.service.CompositeService;
import bert.spring.BertJUnitConfig;
import com.typesafe.config.Config;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

@BertJUnitConfig(locations = "test-application-1.conf")
public class CompositeServiceTest {

    @Autowired
    private CompositeService composite;

    @Autowired
    private Config config;

    @Test
    public void shouldLoadConfigFromTestApplication1() {
        assertEquals(1, config.getInt("value"));
    }

    @Test
    public void shouldTestComposite() {
        assertEquals("even;positive", composite.analyze(14));
        assertEquals("even;not positive", composite.analyze(-14));
    }
}
