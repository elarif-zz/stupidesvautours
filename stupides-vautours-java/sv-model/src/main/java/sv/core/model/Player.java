package sv.core.model;

import java.util.Set;
import java.util.stream.IntStream;
import org.immutables.value.Value.Check;
import org.immutables.value.Value.Immutable;
import org.immutables.value.Value.Parameter;
import org.immutables.value.Value.Style;
import org.immutables.value.Value.Style.ImplementationVisibility;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import sv.core.model.Key.Color;

@Immutable
@Style(visibility = ImplementationVisibility.PACKAGE, overshadowImplementation = true, deepImmutablesDetection=true)
public abstract class Player {
  public abstract ID getId();
  public abstract Color getColor();
  public abstract Set<Card> getDeck();
  public static Player of(final String id, final Color color) {
    final Set<Card> deck = IntStream.range(Key.MIN_VALUE, Key.MAX_VALUE+1).mapToObj(value -> Card.of(value, color)).collect(ImmutableSet.toImmutableSet());
    return ImmutablePlayer.builder().id(ID.of(id)).color(color).deck(deck).build();
  }
  @Check
  public final void checkDeck() {
    Preconditions.checkState(getDeck().stream().allMatch(c -> c.getColor().equals(getColor())));
  }
  public final Player play(final Card card) {
    Preconditions.checkState(getDeck().contains(card));
    final ImmutableSet<Card> newDeck = getDeck().stream().filter(c -> c == card).collect(ImmutableSet.toImmutableSet());
    return ImmutablePlayer.builder().from(this).deck(newDeck).build();
  }
}
