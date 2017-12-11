package sv.core.model;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.generator.InRange;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import sv.core.model.Key.Color;

@RunWith(JUnitQuickcheck.class)
public class CardTest {
  @Property(trials = 250)
  public void testCannotCreateCardWithValueMoreThanMAX_VALUE(
      @InRange(minInt = Key.MAX_VALUE + 1) final int value, final Color color) throws Exception {
    // Given
    final Supplier<Card> builder = () -> Card.of(value, color);
    // When
    catchException(builder).get();
    // Then
    assertThat(caughtException(), instanceOf(IllegalArgumentException.class));
  }

  @Property(trials = 250)
  public void testCannotCreateCardWithValueLessThanMIN_VALUE(
      @InRange(maxInt = Key.MIN_VALUE - 1) final int value, final Color color) throws Exception {
    // Given
    final Supplier<Card> builder = () -> Card.of(value, color);
    // When
    catchException(builder).get();
    // Then
    assertThat(caughtException(), instanceOf(IllegalArgumentException.class));
  }

  @Property(trials = 75)
  public void testCanCreateCardWithValidValue(
      @InRange(minInt = Key.MIN_VALUE, maxInt = Key.MAX_VALUE) final int value, final Color color)
      throws Exception {
    // Given
    final Supplier<Card> builder = () -> Card.of(value, color);
    // When
    final Card card = builder.get();
    // Then
    assertThat(card, notNullValue());
  }

  @Property(trials = 75)
  public void testTwoSimilarCardsAreEqual(
      @InRange(minInt = Key.MIN_VALUE, maxInt = Key.MAX_VALUE) final int value, final Color color)
      throws Exception {
    // Given
    final Boolean expected = true;
    final Card card1 = Card.of(value, color);
    final Card card2 = Card.of(value, color);
    // When
    final Boolean actual = card1 == card2;
    // Then
    assertThat(actual, equalTo(expected));
  }

  @Property
  public void testGetValue(@InRange(minInt = Key.MIN_VALUE, maxInt = Key.MAX_VALUE) final int value,
      final Color color) throws Exception {
    // Given
    final int expected = value;
    // When
    final int actual = Card.of(value, color).getValue();
    // Then
    assertThat(actual, equalTo(expected));
  }

  @Property
  public void testGetColor(@InRange(minInt = Key.MIN_VALUE, maxInt = Key.MAX_VALUE) final int value,
      final Color color) throws Exception {
    // Given
    final Color expected = color;
    // When
    final Color actual = Card.of(value, color).getColor();
    // Then
    assertThat(actual, equalTo(expected));
  }
}
