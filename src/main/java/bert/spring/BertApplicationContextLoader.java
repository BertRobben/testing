package bert.spring;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfigurationAttributes;
import org.springframework.test.context.MergedContextConfiguration;
import org.springframework.test.context.support.AbstractContextLoader;
import org.springframework.test.context.support.AnnotationConfigContextLoaderUtils;

public class BertApplicationContextLoader extends AbstractContextLoader {

    @Override
    public ApplicationContext loadContext(String... strings) {
        BertApplicationContext result = new BertApplicationContext(ConfigFactory.load(strings[0]));
        result.refresh();
        return result;
    }

    @Override
    public void processContextConfiguration(ContextConfigurationAttributes configAttributes) {
        if (!configAttributes.hasClasses() && isGenerateDefaultLocations()) {
            configAttributes.setClasses(AnnotationConfigContextLoaderUtils.detectDefaultConfigurationClasses(configAttributes.getDeclaringClass()));
        }
    }

    @Override
    protected String getResourceSuffix() {
        throw new UnsupportedOperationException(
                "BertApplicationContextLoader does not support the getResourceSuffix() method");
    }

    @Override
    public ApplicationContext loadContext(MergedContextConfiguration mergedConfig) {
        Config config = ConfigFactory.load(mergedConfig.getLocations()[0]);
        var ann = mergedConfig.getTestClass().getAnnotation(BertJUnitConfig.class);
        if (ann != null) {
            for (Class<? extends ConfigInitializer> ci : ann.configInitializers()) {
                try {
                    config = ci.getConstructor().newInstance().apply(config);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
        BertApplicationContext result = new BertApplicationContext(config);
        if (mergedConfig.getClasses().length > 0) {
            result.register(mergedConfig.getClasses());
        }
        result.refresh();
        return result;
    }
}
