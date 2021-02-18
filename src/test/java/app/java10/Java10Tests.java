package app.java10;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.Before;
import org.junit.Test;

public class Java10Tests {

  private List<Integer> list;

  @Before
  public void setUp() {
    list = new ArrayList<>();
    list.add(2);
  }

  // local-var type inference
  @Test
  public void localVariableTypeInferenceString() {
    var message = "Hello, Java 10";
    assertTrue(message instanceof String);
  }

  @Test
  public void localVariableTypeInferenceList() {
    var value = new ArrayList<String>();
    assertTrue(value instanceof List);
  }

  // unmodifiable collections

  @Test(expected = UnsupportedOperationException.class)
  public void modifyCopyOfList() {
    List<Integer> copyList = List.copyOf(list);
    copyList.add(4);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void modifyToUnmodifiableList() {
    List<Integer> evenList = list.stream()
        .filter(i -> i % 2 == 0)
        .collect(Collectors.toUnmodifiableList());
    evenList.add(4);
  }

  @Test
  public void listContainsIntegerOrElseThrow() {
    Integer firstEven = list.stream()
        .filter(i -> i % 2 == 0)
        .findFirst()
        .orElseThrow(); //NoSuchElementException
    assertEquals(Integer.valueOf(2), firstEven);
  }
}
