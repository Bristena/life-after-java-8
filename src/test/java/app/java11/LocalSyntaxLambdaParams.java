package app.java11;

import java.util.Arrays;
import org.junit.Test;

public class LocalSyntaxLambdaParams {

  @Test
  public void localLocalSyntaxLambdaParams () {
    var arrInteger = new Integer[]{5, 9, 3, 6, 2, 4, 8, 7, 1};
    long cnt = Arrays.stream(arrInteger).filter(
        x -> (x != null && x > 5)).count();
    System.out.println(cnt);
  }

}
