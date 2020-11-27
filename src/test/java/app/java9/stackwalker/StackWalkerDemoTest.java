package app.java9.stackwalker;

import org.junit.Test;

public class StackWalkerDemoTest {

  @Test
  public void walkingTheStack() {
    new StackWalkerDemo().methodOne();
  }

}