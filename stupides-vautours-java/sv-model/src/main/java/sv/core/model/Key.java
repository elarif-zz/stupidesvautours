package sv.core.model;

import org.immutables.value.Value.Check;
import org.immutables.value.Value.Immutable;
import org.immutables.value.Value.Parameter;
import org.immutables.value.Value.Style;
import org.immutables.value.Value.Style.ImplementationVisibility;
import com.google.common.base.Preconditions;

@Immutable(intern = true, builder=false)
@Style(visibility = ImplementationVisibility.PACKAGE, overshadowImplementation = true)
public abstract class Key {
  public final static int MIN_VALUE = 1;
  public final static int MAX_VALUE = 15;
  @Parameter(order = 1)
  public abstract int getValue();

  @Parameter(order = 2)
  public abstract Color getColor();
  public static Key of(int value, Color color) {
    return ImmutableKey.of(value, color);
  }
  @Check
  public final void checkValue() {
    Preconditions.checkArgument(getValue() >= MIN_VALUE && getValue() <= MAX_VALUE);
  }

  public enum Color {
    BLUE, GREEN, YELLOW, RED, PINK;
  }
}