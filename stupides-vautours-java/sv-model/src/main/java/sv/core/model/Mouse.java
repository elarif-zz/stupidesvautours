package sv.core.model;

import org.immutables.value.Value.Check;
import org.immutables.value.Value.Immutable;
import org.immutables.value.Value.Parameter;
import org.immutables.value.Value.Style;
import org.immutables.value.Value.Style.ImplementationVisibility;
import com.google.common.base.Preconditions;

@Immutable(intern = true)
@Style(visibility = ImplementationVisibility.PACKAGE, overshadowImplementation = true)
public abstract class Mouse implements Stackable{
  public final static int MIN_VALUE = 1;
  public final static int MAX_VALUE = 10;
  @Parameter
  public abstract int getValue();

  public static Mouse of(int value) {
    return ImmutableMouse.of(value);
  }

  @Check
  public final void checkValue() {
    Preconditions.checkArgument(getValue() >= MIN_VALUE);
    Preconditions.checkArgument(getValue() <= MAX_VALUE);
  }

}
