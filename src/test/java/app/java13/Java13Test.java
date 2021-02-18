package app.java13;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Java13Test {

  @Test
  public void textBlock() {
    String textBlock = """
        SELECT color, year, model
        FROM Car;""";

    String select = "SELECT color, year, model\nFROM Car;";

    assertEquals(select, textBlock);
  }
}
