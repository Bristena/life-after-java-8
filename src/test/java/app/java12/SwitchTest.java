package app.java12;

import static org.junit.Assert.assertEquals;

import java.text.NumberFormat;
import java.util.Locale;
import org.junit.Test;

public class Java12Test {

  @Test
  public void switchCases() {
    //traditional switch
    String day = "SUNDAY";
    switch (day) {
      case "MONDAY":
      case "FRIDAY":
      case "SUNDAY":
        System.out.println(6);
        break;
      case "TUESDAY":
        System.out.println(7);
        break;
      case "THURSDAY":
      case "SATURDAY":
        System.out.println(8);
        break;
      case "WEDNESDAY":
        System.out.println(9);
        break;
    }

    //multiple labels in one switch label
    String result = switch (day) {
      case "MONDAY", "FRIDAY", "SUNDAY" -> "6";
      case "TUESDAY" -> "7";
      case "THURSDAY", "SATURDAY" -> "8";
      case "WEDNESDAY" -> "9";
      default -> "default";
    };

    assertEquals(result, "6");

    //variable in switch
    int numLetters;
    switch (day) {
      case "MONDAY":
      case "FRIDAY":
      case "SUNDAY":
        numLetters = 6;
        break;
      case "TUESDAY":
        numLetters = 7;
        break;
      case "THURSDAY":
      case "SATURDAY":
        numLetters = 8;
        break;
      case "WEDNESDAY":
        numLetters = 9;
        break;
      default:
        throw new IllegalStateException("Wat: " + day);
    }
    System.out.println(numLetters);
  }

  @Test
  public void compactNumber() {
    NumberFormat fmt = NumberFormat
        .getCompactNumberInstance(Locale.US, NumberFormat.Style.LONG);

    assertEquals("100", fmt.format(100));
    assertEquals("1 thousand", fmt.format(1000));
    assertEquals("10 thousand", fmt.format(10000));
    assertEquals("100 thousand", fmt.format(100000));

    NumberFormat fmtShort = NumberFormat
        .getCompactNumberInstance(Locale.US, NumberFormat.Style.SHORT);
    assertEquals("100", fmtShort.format(100));
    assertEquals("1K", fmtShort.format(1000));
    assertEquals("10K", fmtShort.format(10000));
    assertEquals("100K", fmtShort.format(100000));
  }
}
