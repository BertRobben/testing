package bert.jupiter;

import bert.BertApplicationContext;
import com.typesafe.config.ConfigFactory;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.ParameterResolutionDelegate;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;

import java.lang.reflect.Parameter;

public class BertApplicationContextExtension implements BeforeEachCallback, AfterEachCallback, BeforeAllCallback, AfterAllCallback, ParameterResolver, TestInstancePostProcessor {

    private BertApplicationContext context;
    private boolean contextPerTest;

    @Override
    public void afterAll(ExtensionContext extensionContext) {
        System.out.println("afterAll");
        context.close();
    }

    @Override
    public void afterEach(ExtensionContext extensionContext) {
        if (contextPerTest) {
            context.close();
            context = null;
        }
        System.out.println("afterEach " + extensionContext.getRequiredTestMethod());
    }

    @Override
    public void beforeAll(ExtensionContext extensionContext) {
        context = new BertApplicationContext(ConfigFactory.load("test-application-1.conf"));
        context.refresh();
        System.out.println("beforeAll");
    }

    @Override
    public void beforeEach(ExtensionContext extensionContext) {
        if (context == null) {
            context = new BertApplicationContext(ConfigFactory.load("test-application-1.conf"));
            context.refresh();
            contextPerTest = true;
            postProcessTestInstance(extensionContext.getRequiredTestInstance(), extensionContext);
        }
        System.out.println("beforeEach " + extensionContext.getRequiredTestMethod());
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.findAnnotation(Autowired.class).isPresent();
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        Parameter parameter = parameterContext.getParameter();
        int index = parameterContext.getIndex();
        Class<?> testClass = extensionContext.getRequiredTestClass();
        return ParameterResolutionDelegate.resolveDependency(parameter, index, testClass,
                context.getAutowireCapableBeanFactory());
    }

    @Override
    public void postProcessTestInstance(Object o, ExtensionContext extensionContext) {
        AutowireCapableBeanFactory beanFactory = context.getAutowireCapableBeanFactory();
        beanFactory.autowireBeanProperties(o, AutowireCapableBeanFactory.AUTOWIRE_NO, false);
        beanFactory.initializeBean(o, extensionContext.getRequiredTestClass().getName() + AutowireCapableBeanFactory.ORIGINAL_INSTANCE_SUFFIX);
    }
}
