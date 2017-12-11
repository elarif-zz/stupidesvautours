package sv.core.model;

import org.immutables.value.Value.Immutable;
import org.immutables.value.Value.Style;
import org.immutables.value.Value.Style.ImplementationVisibility;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import sv.core.model.Key.Color;

@Immutable(intern = true)
@Style(visibility = ImplementationVisibility.PACKAGE, overshadowImplementation = true, deepImmutablesDetection=true)
public abstract class Card {
  
  public final int getValue() {
    return getKey().getValue();
  }
  public final Color getColor() {
    return getKey().getColor();
  }
  public abstract Key getKey();
  
  private final static LoadingCache<Key, Card> CARDS = CacheBuilder.newBuilder()
      .build(
          new CacheLoader<Key, Card>() {
            public Card load(final Key key) {
              return ImmutableCard.builder().key(key).build();
            }
          });
  public static Card of(int value, Color color) {
    return Card.of(Key.of(value, color));
  }
  public static Card of(Key key) {
      return CARDS.getUnchecked(key);
  }
}
