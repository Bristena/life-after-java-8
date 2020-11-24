package app.domain;

public class Car implements Vehicle {

  public static void main(String[] args) {
    Vehicle car = new Car();
    System.out.println(car.vrumVrum());
  }

}
