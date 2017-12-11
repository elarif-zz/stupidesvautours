package sv.core.model;

import java.io.Serializable;
import org.immutables.value.Value.Immutable;
import sv.core.model.Key.Color;

@Immutable
@Wrapped
public abstract class ID extends Wrapper<String> implements Serializable {
  private static final long serialVersionUID = 4629203356752128256L;
  public static ID of(String value) {
    return ImmutableID.of(value);
  }
}
