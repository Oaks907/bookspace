package com.haifei;

/**
 * Create by haifei on 24/6/2018.
 *
 * -XX:+UnlockDiagnosticVMOptions
 * -XX:+TraceClassLoading
 * -XX:+LogCompilation
 * -XX:LogFile=/tmp/mylogfile.log
 * -XX:+PrintAssembly
 * -XX:+TraceClassLoading
 */
public class HelloWorld {

  public static volatile int i = 1;
  public static void main(String[] args) {
    i = 2;
  }
}
