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

@RunWith(JUnitQuickcheck.class)
public class VultureTest {
  @Property(trials = 250)
  public void testCannotCreateVultureWithValueMoreThanMAX_VALUE( @InRange(minInt=Vulture.MAX_VALUE+1) int value)
      throws Exception {
    // Given
    Supplier<Vulture> builder = () -> Vulture.of(value);
    // When
    catchException(builder).get();
    // Then
    assertThat(caughtException(), instanceOf(IllegalArgumentException.class));
  }

  @Property(trials = 250)
  public void testCannotCreateVultureWithValueLessThanMIN_VALUE(@InRange(maxInt=Vulture.MIN_VALUE-1) int value)
      throws Exception {
    // Given
    Supplier<Vulture> builder = () -> Vulture.of(value);
    // When
    catchException(builder).get();
    // Then
    assertThat(caughtException(), instanceOf(IllegalArgumentException.class));
  }

  @Property(trials = 10)
  public void testCanCreateVultureWithValidValue(@InRange(minInt=Vulture.MIN_VALUE, maxInt=Vulture.MAX_VALUE) int value) throws Exception {
    // Given
    final Supplier<Vulture> builder = () -> Vulture.of(value);
    // When
    Vulture vulture = builder.get();
    // Then
    assertThat(vulture, notNullValue());
  }
  
  @Property
  public void testGetValue(@InRange(minInt = Vulture.MIN_VALUE, maxInt = Vulture.MAX_VALUE) final int value) throws Exception {
    // Given
    final int expected = value;
    // When
    final int actual = Vulture.of(value).getValue();
    // Then
    assertThat(actual, equalTo(expected));
  }

}
