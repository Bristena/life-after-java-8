package app.domain;

public class Calculator implements MyCalculator {

  public static void main(String[] args) {
    MyCalculator calculator = new Calculator();
    System.out.println(calculator.addOddNumbers(1, 2, 3, 4, 5));
    System.out.println(calculator.addEvenNumbers(1, 2, 3, 4, 5));
  }

}
