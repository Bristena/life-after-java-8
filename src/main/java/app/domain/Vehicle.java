package app.domain;

public interface Vehicle {

  default String vrumVrum() {
    return vrum();
  }

  private String vrum() {
    return "Vrum vrum from the private method";
  }
}
