package sv.core.model;

import org.immutables.value.Value.Check;
import org.immutables.value.Value.Immutable;
import org.immutables.value.Value.Parameter;
import org.immutables.value.Value.Style;
import org.immutables.value.Value.Style.ImplementationVisibility;
import com.google.common.base.Preconditions;

@Immutable(intern = true)
@Style(visibility = ImplementationVisibility.PACKAGE, overshadowImplementation = true)
public abstract class Vulture implements Stackable{
  public final static int MIN_VALUE = -5;
  public final static int MAX_VALUE = -1;
  @Parameter
  public abstract int getValue();

  public static Vulture of(int value) {
    return ImmutableVulture.of(value);
  }

  @Check
  public final void checkValue() {
    Preconditions.checkArgument(getValue() >= MIN_VALUE);
    Preconditions.checkArgument(getValue() <= MAX_VALUE);
  }

}
