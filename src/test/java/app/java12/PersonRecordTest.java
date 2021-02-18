package app.java12;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import app.java14.Person;
import org.junit.Test;

public class PersonRecordTest {

  @Test
  public void validPerson() {
    String name = "Santos Miguel";
    String address = "15th Avenue";

    Person person = new Person(name, address);

    assertEquals(name, person.name());
    assertEquals(address, person.address());
  }

  @Test
  public void equalsPerson() {
    String name = "Santos Miguel";
    String address = "15th Avenue";

    Person person1 = new Person(name, address);
    Person person2 = new Person(name, address);

    assertTrue(person1.equals(person2));
  }

  @Test
  public void samePersonSameHash() {
    String name = "Santos Miguel";
    String address = "15th Avenue";

    Person person1 = new Person(name, address);
    Person person2 = new Person(name, address);

    assertEquals(person1.hashCode(), person2.hashCode());
  }

  @Test
  public void recordToString() {
    String name = "Santos Miguel";
    String address = "15th Avenue";

    Person person = new Person(name, address);

    String expectedString = "Person[name=Santos Miguel, address=15th Avenue]";

    assertEquals(expectedString, person.toString());
  }
}
