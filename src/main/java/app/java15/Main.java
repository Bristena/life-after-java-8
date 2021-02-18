package app.java15;

public class Main {

  public static void main(String[] args) {
//    Vehicle vehicle = new Car(4, "CJ-01-ABC");
    Vehicle vehicle = new Truck(2, "CJ-02-ABC");
    if (vehicle instanceof Car car) {
      System.out.println(car.getNumberOfSeats());
    } else if (vehicle instanceof Truck truck) {
      System.out.println(truck.getLoadCapacity());
    } else {
      throw new RuntimeException("Unknown instance of Vehicle");
    }
  }
}
