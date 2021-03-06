package app.java9;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.junit.Test;

public class CompletableFutureUnitTest {

  /**
   * Delay the completion of a CompletableFuture with a specific value by one second
   *
   * @throws InterruptedException
   */
  @Test
  public void delayCompletableFuture() throws InterruptedException {
    Object input = new Object();

    CompletableFuture<Object> completableFuture = new CompletableFuture();
    completableFuture
        .completeAsync(() -> input, CompletableFuture.delayedExecutor(1, TimeUnit.SECONDS));
    Thread.sleep(100);

    assertFalse(completableFuture.isDone());

    Thread.sleep(1000);
    assertTrue(completableFuture.isDone());
  }

  /**
   * If the complete feature is not finished in a period of time, then TimeoutException is thrown
   *
   * @throws InterruptedException
   */
  @Test
  public void timeoutException() throws InterruptedException {
    CompletableFuture<Integer> completableFuture =
        CompletableFuture.supplyAsync(() -> {
          try {
            TimeUnit.SECONDS.sleep(5);
          } catch (InterruptedException ex) {
            ex.printStackTrace();
          }
          return 3;
        }).orTimeout(1, TimeUnit.SECONDS);

    TimeUnit.SECONDS.sleep(2);

    assertTrue(completableFuture.isDone());

    try {
      completableFuture.get();
    } catch (ExecutionException | InterruptedException ex) {
      assertTrue(ex.getCause() instanceof TimeoutException);
    }
  }

  @Test
  public void timeout() throws InterruptedException {
    Object input = new Object();
    CompletableFuture<Object> completableFuture = new CompletableFuture();
    completableFuture.orTimeout(1, TimeUnit.SECONDS);

    Thread.sleep(100);

    completableFuture.complete(input);

    Thread.sleep(1000);

    assertTrue(completableFuture.isDone());
  }

  /**
   * A delayed result can be achieved by using the completeOnTimeout method. This will be resolved
   * with a given input if it stays unresolved after 1 second.
   *
   * @throws InterruptedException
   */
  @Test
  public void completeOnTimeout() throws InterruptedException, ExecutionException {
    CompletableFuture<Integer> completableFuture =
        CompletableFuture.supplyAsync(() -> {
          try {
            TimeUnit.SECONDS.sleep(5);
          } catch (InterruptedException ex) {
            ex.printStackTrace();
          }
          return 3;
        }).completeOnTimeout(4, 1, TimeUnit.SECONDS);

    Thread.sleep(2000);

    assertTrue(completableFuture.isDone());
    assertEquals(4, completableFuture.get().intValue());
  }
}


