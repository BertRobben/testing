package bert.test;

import bert.spring.BertApplicationContextLoader;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.core.annotation.AliasFor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextLoader;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Inspired on {@link org.springframework.test.context.junit.jupiter.SpringJUnitConfig} but sets the
 * {@link ContextLoader} to {@link BertApplicationContextLoader}.
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface BertJUnitConfig {

    @AliasFor(annotation = ContextConfiguration.class, attribute = "classes")
    Class<?>[] value() default {};

    @AliasFor(annotation = ContextConfiguration.class)
    Class<?>[] classes() default {};

    @AliasFor(annotation = ContextConfiguration.class)
    String[] locations() default { "test-application.conf" };

    @AliasFor(annotation = ContextConfiguration.class)
    Class<? extends ApplicationContextInitializer<?>>[] initializers() default {};

    @AliasFor(annotation = ContextConfiguration.class)
    boolean inheritLocations() default true;

    @AliasFor(annotation = ContextConfiguration.class)
    boolean inheritInitializers() default true;

    @AliasFor(annotation = ContextConfiguration.class)
    String name() default "";

    @AliasFor(annotation = ContextConfiguration.class)
    Class<? extends ContextLoader> loader() default BertApplicationContextLoader.class;

}
