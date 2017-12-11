package sv.core.model;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.runner.RunWith;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.generator.InRange;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import sv.core.model.Key.Color;

@RunWith(JUnitQuickcheck.class)
public class ColorTest {
  @Property
  public void testGetValue(final Color color) throws Exception {
    // Given
    final Color expected = color;
    // When
    final Color actual = Color.valueOf(color.name());
    // Then
    assertThat(actual, equalTo(expected));
  }
}
