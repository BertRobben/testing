package bert.test;

import static org.junit.jupiter.api.Assertions.*;

import bert.service.CompositeService;
import bert.service.EvenService;
import bert.spring.BertApplicationContextLoader;
import com.typesafe.config.Config;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "test-application-1.conf", loader = BertApplicationContextLoader.class)
public class EvenServiceTest {

    @Autowired
    private EvenService service;

    @Autowired
    private Config config;

    @Test
    public void shouldTestEven() {
        assertEquals(true, service.isEven(6));
        assertEquals(false, service.isEven(3));
    }

    @Test
    public void shouldLoadConfigFromTestApplication1() {
        assertEquals(1, config.getInt("value"));
    }

}
