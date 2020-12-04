package app.java9.streams;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Test;

public class StreamsImprovementsTest {

  @Test
  public void takeWhileOrdered() {
    Stream<String> orderedStream = Stream.of("a", "a", "b", "c", "a");
    List<String> resultList = orderedStream.takeWhile(s -> s.equals("a"))
        .collect(Collectors.toList());

    assertEquals(List.of("a", "a"), resultList);
  }

  @Test
  public void dropWhileOrdered() {
    Stream<String> orderedStream = Stream.of("a", "a", "b", "c", "a");
    List<String> resultList = orderedStream.dropWhile(s -> s.equals("a"))
        .collect(Collectors.toList());

    assertEquals(List.of("b", "c", "a"), resultList);
  }

  @Test
  public void takeWhileUnordered() {
    Stream<String> unorderedStream = Stream.of("d", "a", "m", "a", "b", "c");
    List<String> resultList = unorderedStream.takeWhile(s -> s.equals("a"))
        .collect(Collectors.toList());
    // prints out a different subset of matching elements every time
    // an empty set is also a subset
    System.out.println(resultList);
  }

  @Test
  public void dropWhileUnordered() {
    Stream<String> unorderedStream = Stream.of("d", "a", "m", "a", "b", "c");
    List<String> resultList = unorderedStream.dropWhile(s -> s.equals("a"))
        .collect(Collectors.toList());
    // prints out a different subset of matching elements every time
    // an empty set is also a subset
    System.out.println(resultList);
  }

  @Test
  public void ofNullableNotNull() {
    Stream<String> isNotNull = Stream.ofNullable("caramele");
    assertTrue(isNotNull.findAny().isPresent());
  }

  @Test
  public void ofNullableNull() {
    Stream<String> isNotNull = Stream.ofNullable(null);
    assertTrue(isNotNull.findAny().isEmpty());
  }

  @Test
  public void iterate() {
    System.out.println("-----------Stream iterate-------------------");
    Stream.iterate(0, i -> i < 10, i -> i + 1)
        .forEach(System.out::print);

    System.out.println("\n-----------Traditional for-----------------");
    for (int i = 0; i < 10; ++i) {
      System.out.print(i);
    }

  }
}
