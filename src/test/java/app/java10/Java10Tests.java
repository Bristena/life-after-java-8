package app.java10;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class Java10Tests {

  @Test
  public void localVariableTypeInference() {
    var message = "Hello, Java 10";
    assertTrue(message instanceof String);
  }

}
