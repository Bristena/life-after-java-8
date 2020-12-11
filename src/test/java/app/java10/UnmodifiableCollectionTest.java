package app.java10;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import org.junit.Test;

public class UnmodifiableCollectionTest {

  @Test(expected = UnsupportedOperationException.class)
  public void copyOf() {
    List<Integer> someList = new ArrayList<>();
    someList.add(1);
    someList.add(2);

    List<Integer> copyOfSomeList = List.copyOf(someList);
    copyOfSomeList.add(3);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void toUnmodifiable() {
    List<Integer> someList = new ArrayList<>();
    someList.add(1);
    someList.add(2);
    List<Integer> evenList = someList.stream()
        .filter(i -> i % 2 == 0)
        .collect(Collectors.toUnmodifiableList());
    evenList.add(4);
  }

  @Test
  public void optionalOrElseThrow() {
    List<Integer> someList = List.of(1, 2, 4);
    Integer firstEven = someList.stream()
        .filter(i -> i % 2 == 0)
        .findFirst()
        .orElseThrow();
    assertEquals(Integer.valueOf(2), firstEven);
  }

  @Test(expected = NoSuchElementException.class)
  public void optionalOrElseThrowException() {
    List<Integer> someList = List.of(1, 3, 5);
    Integer firstEven = someList.stream()
        .filter(i -> i % 2 == 0)
        .findFirst()
        .orElseThrow();
  }
}
