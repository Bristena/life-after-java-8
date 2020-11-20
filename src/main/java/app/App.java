package app;

import app.java9.stackwalker.StackWalkerDemo;

public class App {

  public static void main(String[] args) {
    StackWalkerDemo stackWalkerDemo = new StackWalkerDemo();
    stackWalkerDemo.methodOne();
    stackWalkerDemo.findCaller();
  }
}
