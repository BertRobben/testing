package bert.test;

import bert.service.CompositeService;
import bert.service.EvenService;
import bert.spring.BertApplicationContextLoader;
import com.typesafe.config.Config;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "test-application-1.conf", loader = BertApplicationContextLoader.class)
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
