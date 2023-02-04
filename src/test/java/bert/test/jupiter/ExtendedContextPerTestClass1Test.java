package bert.test.jupiter;

import com.typesafe.config.Config;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExtendedContextPerTestClass1Test extends AbstractContextPerTestClassTest {

    @Autowired
    private Config config;

    @Test
    public void shouldLoadConfigFromTestApplication1() {
        assertEquals(1, config.getInt("value"));
    }

}
