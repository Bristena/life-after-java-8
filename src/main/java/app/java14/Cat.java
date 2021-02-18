package app.java14;

public class Cat implements Animal {

  private String name;


  public String meow() {
    return "meow";
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
