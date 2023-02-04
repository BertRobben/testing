package bert.test.jupiter;

import bert.jupiter.BertApplicationContextExtension;
import bert.service.EvenService;
import com.typesafe.config.Config;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(BertApplicationContextExtension.class)
public class EvenServiceTest {

    @Autowired
    private EvenService service;

    @Autowired
    private Config config;

    @Test
    public void shouldTestEven() {
        assertTrue(service.isEven(6));
        assertFalse(service.isEven(3));
    }

    @Test
    public void shouldLoadConfigFromTestApplication1() {
        assertEquals(1, config.getInt("value"));
    }

    @Test
    public void shouldInjectParameters(@Autowired Config config) {
        assertEquals(this.config, config);
    }
}
