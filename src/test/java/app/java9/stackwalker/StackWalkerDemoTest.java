package app.java9.stackwalker;

import org.junit.Test;

public class StackWalkerDemoTest {

  @Test
  public void giveStalkWalker_whenWalkingTheStack_thenShowStackFrames() {
    new StackWalkerDemo().methodOne();
  }

}