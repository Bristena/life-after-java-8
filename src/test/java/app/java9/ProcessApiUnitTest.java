package app.java9;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Stream;
import org.junit.Test;

public class ProcessApiUnitTest {

  @Test
  public void infoCurrentProcess() {
    ProcessHandle processHandle = ProcessHandle.current();
    ProcessHandle.Info info = processHandle.info();
    long pid = processHandle.pid();

    System.out.println("pid: " + pid);
    assertFalse(info.arguments().isPresent());
    assertTrue(info.command().isPresent());
    assertTrue(info.command().get().contains("java"));
    assertTrue(info.startInstant().isPresent());
    assertTrue(info.totalCpuDuration().isPresent());
    assertTrue(info.user().isPresent());
  }

  @Test
  public void infoChildProcess() throws IOException {
    String javaCmd = "java";
    ProcessBuilder processBuilder = new ProcessBuilder(javaCmd, "-version");
    Process process = processBuilder.inheritIO().start();
    ProcessHandle processHandle = process.toHandle();
    ProcessHandle.Info processInfo = processHandle.info();

    System.out.println("Child pid: " + processHandle.pid());
    assertFalse(processInfo.arguments().isPresent());
    assertTrue(processInfo.command().isPresent());
    assertTrue(processInfo.command().get().contains("java"));
    assertTrue(processInfo.startInstant().isPresent());
    assertTrue(processInfo.totalCpuDuration().isPresent());
    assertTrue(processInfo.user().isPresent());
  }

  @Test
  public void exitChildProcess() throws ExecutionException, InterruptedException, IOException {
    String javaCmd = "java";
    ProcessBuilder processBuilder = new ProcessBuilder(javaCmd, "-version");
    Process process = processBuilder.inheritIO().start();
    ProcessHandle processHandle = process.toHandle();

    System.out.println("PID: " + processHandle.pid() + " has started");
    CompletableFuture<ProcessHandle> onProcessExit = processHandle.onExit();
    onProcessExit.get();
    assertFalse(processHandle.isAlive());
    onProcessExit.thenAccept(ph -> {
      System.out.println("PID: " + ph.pid() + " has stopped");
    });
  }

  @Test
  public void enumerateChildProcesses() throws IOException {
    int childProcessCount = 5;
    for (int i = 0; i < childProcessCount; i++) {
      String javaCmd = "java";
      ProcessBuilder processBuilder
          = new ProcessBuilder(javaCmd, "-version");
      processBuilder.inheritIO().start();
    }

    Stream<ProcessHandle> children = ProcessHandle.current().children();
    children.filter(ProcessHandle::isAlive)
        .forEach(ph -> System.out.println("PID child: " + ph.pid() + " cmd: " + ph.info().command()));
    Stream<ProcessHandle> descendants = ProcessHandle.current().descendants();
    descendants.filter(ProcessHandle::isAlive)
        .forEach(ph -> System.out.println("PID descendent: " + ph.pid() + " cmd: " + ph.info().command()));
  }

  @Test
  public void enumerateLiveProcesses() {
    Stream<ProcessHandle> liveProcesses = ProcessHandle.allProcesses();
    liveProcesses.filter(ProcessHandle::isAlive)
        .forEach(ph -> {
          System.out.println("Live process id: " + ph.pid());
          assertTrue(ph.info().command().isPresent());
          assertTrue(ph.info().startInstant().isPresent());
          assertTrue(ph.info().totalCpuDuration().isPresent());
          assertTrue(ph.info().user().isPresent());
        });
  }
}
