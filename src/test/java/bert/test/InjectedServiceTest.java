package bert.test;

import bert.service.CompositeService;
import bert.service.PositiveService;
import com.typesafe.config.Config;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;

import static org.junit.jupiter.api.Assertions.assertEquals;

@BertJUnitConfig(classes = InjectedServiceTest.AlwaysPositiveService.class, locations = "test-application-2.conf")
public class InjectedServiceTest {

    @Autowired
    private CompositeService composite;

    @Autowired
    private Config config;

    @Test
    public void shouldLoadConfigFromTestApplication1() {
        assertEquals(2, config.getInt("value"));
    }

    @Test
    public void shouldAlwaysBePositive() {
        assertEquals("even;positive", composite.analyze(14));
        assertEquals("even;positive", composite.analyze(-14));
        assertEquals("not even;positive", composite.analyze(-5));
    }

    @Primary
    public static class AlwaysPositiveService implements PositiveService {

        @Override
        public boolean isPositive(int number) {
            return true;
        }
    }
}
