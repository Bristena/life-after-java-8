package app.java9.stackwalker;

import java.lang.StackWalker.StackFrame;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StackWalkerDemo {

  public void methodTree() {
    System.out.println("-------------------Entire Stack Trace--------------------------");
    List<StackFrame> stackTrace = StackWalker.getInstance()
        .walk(this::walkExample);

    printStackTrace(stackTrace);

    System.out.println("----------------Filtered Stack Trace-----------------------------");

    stackTrace = StackWalker.getInstance().walk(this::walkExample2);

    printStackTrace(stackTrace);

    System.out.println("-----------------Identify the caller----------------------------");

    String line = StackWalker.getInstance().walk(this::walkExample3);
    System.out.println(line);
  }

  public List<StackFrame> walkExample(Stream<StackFrame> stackFrameStream) {
    return stackFrameStream.collect(Collectors.toList());
  }

  public List<StackFrame> walkExample2(Stream<StackFrame> stackFrameStream) {
    return stackFrameStream.filter(frame -> frame.getClassName()
        .contains("app.java9.stackwalker"))
        .collect(Collectors.toList());
  }

  public String walkExample3(Stream<StackFrame> stackFrameStream) {
    return stackFrameStream.filter(frame -> frame.getClassName()
        .contains("app.java9.stackwalker")
        && frame.getClassName()
        .endsWith("Test"))
        .findFirst()
        .map(frame -> frame.getClassName() + " Method: " + frame.getMethodName() + ", Line " + frame
            .getLineNumber())
        .orElse("Unknown caller");
  }

  public void findCaller() {
    Class<?> caller = StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE)
        .getCallerClass();
    System.out.println(caller.getCanonicalName());
  }

  public void printStackTrace(List<StackFrame> stackTrace) {
    for (StackFrame stackFrame : stackTrace) {
      System.out.println(
          stackFrame.getClassName() + " Method: " + stackFrame.getMethodName() + ", Line "
              + stackFrame
              .getLineNumber());
    }
  }

  public void methodTwo() {
    methodTree();
  }

  public void methodOne() {
    methodTwo();
  }

}
