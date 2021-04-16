package app.java10;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class Java10Tests {

  // local-var type inference
  @Test
  public void localVariableTypeInferenceString() {
    var message = "Hello, Java 10";
    assertTrue(message instanceof String);
  }

  @Test
  public void localVariableTypeInferenceList() {
    var value = new ArrayList<String>();
    assertTrue(value instanceof List);
  }
}
