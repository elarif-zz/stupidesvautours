package sv.core.model;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import java.util.function.Supplier;
import org.junit.runner.RunWith;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.generator.InRange;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import sv.core.model.Key.Color;

@RunWith(JUnitQuickcheck.class)
public class MouseTest {
  @Property(trials = 250)
  public void testCannotCreateMouseWithValueMoreThanMAX_VALUE( @InRange(minInt=Mouse.MAX_VALUE+1) int value)
      throws Exception {
    // Given
    Supplier<Mouse> builder = () -> Mouse.of(value);
    // When
    catchException(builder).get();
    // Then
    assertThat(caughtException(), instanceOf(IllegalArgumentException.class));
  }

  @Property(trials = 250)
  public void testCannotCreateMouseWithValueLessThanMIN_VALUE(@InRange(maxInt=Mouse.MIN_VALUE-1) int value)
      throws Exception {
    // Given
    Supplier<Mouse> builder = () -> Mouse.of(value);
    // When
    catchException(builder).get();
    // Then
    assertThat(caughtException(), instanceOf(IllegalArgumentException.class));
  }

  @Property(trials = 10)
  public void testCanCreateMouseWithValidValue(@InRange(minInt=Mouse.MIN_VALUE, maxInt=Mouse.MAX_VALUE) int value) throws Exception {
    // Given
    final Supplier<Mouse> builder = () -> Mouse.of(value);
    // When
    Mouse mouse = builder.get();
    // Then
    assertThat(mouse, notNullValue());
  }
  
  @Property
  public void testGetValue(@InRange(minInt = Mouse.MIN_VALUE, maxInt = Mouse.MAX_VALUE) final int value) throws Exception {
    // Given
    final int expected = value;
    // When
    final int actual = Mouse.of(value).getValue();
    // Then
    assertThat(actual, equalTo(expected));
  }
}
