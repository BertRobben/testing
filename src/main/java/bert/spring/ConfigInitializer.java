package bert.spring;

import com.typesafe.config.Config;

import java.util.function.UnaryOperator;

public interface ConfigInitializer extends UnaryOperator<Config> {
}
