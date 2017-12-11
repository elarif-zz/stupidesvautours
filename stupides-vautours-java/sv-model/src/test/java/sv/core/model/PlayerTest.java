package sv.core.model;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.runner.RunWith;
import com.google.common.collect.ImmutableSet;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import sv.core.model.Key.Color;

@RunWith(JUnitQuickcheck.class)
public class PlayerTest {
  @Property
  public void testCanCreateValidPlayer(String id, Color color) throws Exception {
    // When
    Player player = Player.of(id, color);
    // Then
    assertThat(player, notNullValue());
  }

  @Property
  public void testValidPlayerHasValidDeck(String id, Color color) throws Exception {
    // Given
    int expected = 1 + Key.MAX_VALUE - Key.MIN_VALUE;
    // When
    Player player = Player.of(id, color);
    // Then
    final int actual = player.getDeck().size();
    assertThat(actual, equalTo(expected));
  }

  @Property
  public void testPlayerCanPlayValidCard(String id, Color color) throws Exception {
    // Given
    final Player player = Player.of(id, color);
    final Card card =
        Card.of(ThreadLocalRandom.current().nextInt(Key.MIN_VALUE, Key.MAX_VALUE), color);
    final Set<Card> newDeck =
        player.getDeck().stream().filter(c -> c == card).collect(Collectors.toSet());
    final Player expected = ImmutablePlayer.builder().from(player).deck(newDeck).build();
    // When
    final Player actual = player.play(card);
    // Then
    assertThat(actual, equalTo(expected));
  }

  @Property
  public void testPlayerCannotPlayInvalidColor(String id, Color color) throws Exception {
    // Given
    final Player player = Player.of(id, color);
    final Color anyOtherColor =
        Arrays.asList(Color.values()).stream().filter(c -> c != color).findAny().get();
    final Card card =
        Card.of(ThreadLocalRandom.current().nextInt(Key.MIN_VALUE, Key.MAX_VALUE), anyOtherColor);
    Supplier<Player> supplier = () -> player.play(card);
    // When
    catchException(supplier).get();
    // Then
    assertThat(caughtException(), instanceOf(IllegalStateException.class));
  }

  @Property
  public void testCheckDeck(String id, Color color) throws Exception {
    // Given
    
    final Color anyOtherColor =
        Arrays.asList(Color.values()).stream().filter(c -> c != color).findAny().get();
    final Set<Card> deck = IntStream.range(Key.MIN_VALUE, Key.MAX_VALUE+1).mapToObj(value -> Card.of(value, anyOtherColor)).collect(ImmutableSet.toImmutableSet());
    
    final Supplier<Player> supplier = () -> ImmutablePlayer.builder().id(ID.of(id)).color(color).deck(deck).build();
    // When
    catchException(supplier).get();
    // Then
    assertThat(caughtException(), instanceOf(IllegalStateException.class));
  }
}
