package sv.core.model;

import org.immutables.value.Value.Parameter;

public abstract class Wrapper<T> {
@Parameter
public abstract T value();
}
