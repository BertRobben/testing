package bert.test.jupiter;

import bert.jupiter.BertApplicationContextExtension;
import bert.service.EvenService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class AbstractContextPerTestClassTest {

    @RegisterExtension
    private static BertApplicationContextExtension extension = new BertApplicationContextExtension();

    @Autowired
    protected EvenService service;

    @Test
    public void shouldTestEven() {
        assertTrue(service.isEven(6));
        assertFalse(service.isEven(3));
    }

}
