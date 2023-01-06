package bert.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfigurationAttributes;
import org.springframework.test.context.MergedContextConfiguration;
import org.springframework.test.context.support.AbstractContextLoader;
import org.springframework.test.context.support.AnnotationConfigContextLoaderUtils;

public class BertApplicationContextLoader extends AbstractContextLoader {

    @Override
    public ApplicationContext loadContext(String... strings) {
        BertApplicationContext result = new BertApplicationContext(strings[0]);
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
        BertApplicationContext result = new BertApplicationContext(mergedConfig.getLocations()[0]);
        if (mergedConfig.getClasses().length > 0) {
            result.register(mergedConfig.getClasses());
        }
        result.refresh();
        return result;
    }
}
