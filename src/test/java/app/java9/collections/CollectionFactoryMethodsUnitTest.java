package app.java9.collections;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;

public class CollectionFactoryMethodsUnitTest {

  //List
  @Test
  public void createLists() {
    // create traditional list
    List<String> traditionalList = new ArrayList<>();
    traditionalList.add("a");
    traditionalList.add("b");
    traditionalList.add("c");

    List<String> factoryList = List.of("a", "b", "c");

    assertEquals(traditionalList, factoryList);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void addInFactoryList() {
    List<String> factoryList = List.of("a", "b", "c");
    factoryList.add("d");
  }

  @Test(expected = UnsupportedOperationException.class)
  public void removeFromFactoryList() {
    List<String> factoryList = List.of("a", "b", "c");
    factoryList.remove("a");
  }

  @Test(expected = UnsupportedOperationException.class)
  public void updateInFactoryList() {
    List<String> factoryList = List.of("a", "b", "c");
    factoryList.set(0, "d");
  }

  @Test(expected = NullPointerException.class)
  public void nullInFactoryList() {
    List.of("a", "b", null);
  }

  @Test
  public void factoryListIsNotArrayList() {
    List<String> factoryList = List.of("a", "b");

    assertFalse(factoryList instanceof ArrayList);
  }

  @Test
  public void factoryListOfArrays() {
    List<String> factoryList = List.of("a", "b");
    assertFalse(factoryList instanceof ArrayList);
  }

  @Test
  public void ifListSizeIsOne_thenSuccess() {
    int[] arr = {1, 2, 3, 4};
    List<int[]> list = List.of(arr);
    assertEquals(1, list.size());
    assertArrayEquals(arr, list.get(0));
  }

  //Map
  @Test
  public void createMaps() {
    Map<Integer, String> traditionalMap = new HashMap<>();
    traditionalMap.put(1, "a");
    traditionalMap.put(2, "b");
    traditionalMap.put(3, "c");

    Map<Integer, String> factoryMap = Map.of(1, "a", 2, "b", 3, "c");

    assertEquals(traditionalMap, factoryMap);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void addInFactoryMap() {
    Map<Integer, String> factoryMap = Map.of(1, "a", 2, "b", 3, "c");
    factoryMap.put(4, "d");
  }

  @Test(expected = UnsupportedOperationException.class)
  public void removeFromFactoryMap() {
    Map<Integer, String> factoryMap = Map.of(1, "A", 2, "b", 3, "c");
    factoryMap.remove(2);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void updateInFactoryMap() {
    Map<Integer, String> factoryMap = Map.of(1, "A", 2, "b", 3, "c");
    factoryMap.put(3, "d");
  }

  @Test(expected = NullPointerException.class)
  public void nullInFactoryMap() {
    Map.of(1, "A", 2, "b", null, "c");
  }

  @Test(expected = IllegalArgumentException.class)
  public void factoryMapDuplicateKey() {
    Map.of(1, "A", 2, "b", 2, "c");
  }

  @Test(expected = NullPointerException.class)
  public void nullValueInFactoryMap() {
    Map.of("foo", "a", "bar", null);
  }

  @Test
  public void factoryMapIsNotHashMap() {
    Map<String, String> map = Map.of("foo", "a", "bar", "b");
    assertFalse(map instanceof HashMap);
  }

}

