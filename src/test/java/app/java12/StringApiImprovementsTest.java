package app.java12;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StringApiImprovementsTest {

  @Test
  public void indent() {
    String input = "Hello, I am Bristena";
    String output1 = input.indent(5); //adds 5 whitespaces and \n at the end
    String expectedOutput = "     Hello, I am Bristena\n";

    assertEquals(expectedOutput, output1);
    System.out.println(input.length());
    System.out.println(output1.length());

    String output2 = output1.indent(-5); //removes the whitespaces, the \n remains
    expectedOutput = "Hello, I am Bristena\n";
    assertEquals(expectedOutput, output2);

    String output3 = input.indent(0); //addes a \n at the end
    assertEquals(expectedOutput, output3);
  }

  @Test
  public void transform() {
    String result = "hello".transform(input -> input + " world!");
    String expectedValue = "hello world!";
    assertEquals(expectedValue, result);

    result = "Does it replace the letter a with letter b?"
        .transform(input -> input.replace('a', 'b'));
    expectedValue = "Does it replbce the letter b with letter b?";
    assertEquals(expectedValue, result);
  }
}
