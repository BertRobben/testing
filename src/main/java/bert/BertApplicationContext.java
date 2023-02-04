package bert;

import com.typesafe.config.Config;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BertApplicationContext extends AnnotationConfigApplicationContext {

    private final Config config;

    public BertApplicationContext(Config config) {
        this.config = config;
        addComponents();
        registerBean(Config.class.getName(), Config.class, () -> config);
    }

    private void addComponents() {
        register(config.getStringList("components").stream().map(this::forName).toList().toArray(new Class<?>[0]));
    }

    private Class<?> forName(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
