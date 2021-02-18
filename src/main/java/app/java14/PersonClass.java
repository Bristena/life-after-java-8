package app.java14;

import java.util.Objects;

public class PersonClass {

  private final String name;
  private final String address;

  public PersonClass(String name, String address) {
    this.name = name;
    this.address = address;
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, address);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    } else if (!(obj instanceof PersonClass)) {
      return false;
    } else {
      PersonClass other = (PersonClass) obj;
      return Objects.equals(name, other.name)
          && Objects.equals(address, other.address);
    }
  }

  @Override
  public String toString() {
    return "PersonClass [name=" + name + ", address=" + address + "]";
  }

  public String getName() {
    return name;
  }

  public String getAddress() {
    return address;
  }
}
