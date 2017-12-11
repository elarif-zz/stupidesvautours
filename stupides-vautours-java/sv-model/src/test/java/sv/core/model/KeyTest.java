package sv.core.model;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
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
public class KeyTest {
  @Property(trials = 250)
  public void testCannotCreateKeyWithValueMoreThanMAX_VALUE( @InRange(minInt=Key.MIN_VALUE+1) int value, Color color)
      throws Exception {
    // Given
    Supplier<Key> builder = () -> Key.of(value, color);
    // When
    catchException(builder).get();
    // Then
    assertThat(caughtException(), instanceOf(IllegalArgumentException.class));
  }

  @Property(trials = 250)
  public void testCannotCreateKeyWithValueLessThanMIN_VALUE(@InRange(maxInt=Key.MIN_VALUE-1) int value, Color color)
      throws Exception {
    // Given
    Supplier<Key> builder = () -> Key.of(value, color);
    // When
    catchException(builder).get();
    // Then
    assertThat(caughtException(), instanceOf(IllegalArgumentException.class));
  }

  @Property(trials = 75)
  public void testCanCreateKeyWithValidValue(@InRange(minInt=Key.MIN_VALUE, maxInt=Key.MAX_VALUE) int value, Color color) throws Exception {
    // Given
    final Supplier<Key> builder = () -> Key.of(value, color);
    // When
    Key key = builder.get();
    // Then
    assertThat(key, notNullValue());
  }

}
