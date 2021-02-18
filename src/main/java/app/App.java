package app;

import app.java14.Cat;

public class App {

  public static void main(String[] args) {
//    TestDeprecated testDeprecated = new TestDeprecated();
//    testDeprecated.doNothing();

    Cat cat = null;
    cat.getName(); //Cannot invoke "app.java14.Cat.getName()" because "cat" is null
  }
}
