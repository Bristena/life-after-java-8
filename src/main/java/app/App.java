package app;

import app.domain.TestDeprecated;

public class App {

  public static void main(String[] args) {
    System.out.println("Hello world");
    TestDeprecated testDeprecated = new TestDeprecated();
    testDeprecated.doNothing();
  }
}
