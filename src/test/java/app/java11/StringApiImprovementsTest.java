package app.java11;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class StringApiImprovementsTest {

  @Test
  public void repeat() {
    String output = "Happy ".repeat(2) + "Christmas";

    assertEquals("Happy Happy Christmas", output);
  }

  @Test
  public void isBlank() {
    String input = "TEST";
    assertFalse(input.isBlank());

    input = " ";
    assertTrue(input.isBlank());

    input = "";
    assertTrue(input.isBlank());
  }

  @Test
  public void strip() {
    String input = "   TEST   ";
    String output = input.strip();

    assertEquals("TEST", output);

    input = "\n\t  hello   \u2005";
    output = input.strip();

    assertEquals("hello", output);

    output = input.trim();

    assertEquals("hello   \u2005", output);
  }

  @Test
  public void stripLeading() {
    String input = "   test  ";
    String output = input.stripLeading();

    assertEquals("test  ", output);
  }

  @Test
  public void stripTrailing() {
    String input = "   test  ";
    String output = input.stripTrailing();

    assertEquals("   test", output);
  }

  @Test
  public void lines() {
    String multilineStr = "This is\n a \n multiline\n string.";

    long lineCount = multilineStr.lines().count();

    assertEquals(4, lineCount);
  }
}
