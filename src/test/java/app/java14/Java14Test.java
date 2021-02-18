package app.java14;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Java14Test {

  @Test
  public void patternMatchingInstanceOf() {
    //old way
    Animal animal = new Cat();
    String result = null;
    if (animal instanceof Cat) {
      Cat cat = (Cat) animal;
      result = cat.meow();
    } else if (animal instanceof Dog) {
      Dog dog = (Dog) animal;
      result = dog.woof();
    }

    assertEquals("meow", result);

    //new way
    animal = new Dog();
    if (animal instanceof Cat cat) {
      result = cat.meow();
    } else if (animal instanceof Dog dog) {
      result = dog.woof();
    }

    assertEquals("woof", result);
  }
}
