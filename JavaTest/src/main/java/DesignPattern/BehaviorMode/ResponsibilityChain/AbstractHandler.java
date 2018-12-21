package DesignPattern.BehaviorMode.ResponsibilityChain;

import lombok.Setter;

/**
 * Create by haifei on 21/12/2018 3:55 PM.
 * 1。自己无法处理，向下传递
 * 2。自己处理失败，向下传递
 * 3。不应该自己处理，向下传递
 *
 * 下面的例子是自己处理失败，向下传递的例子
 */
@Setter
public abstract class AbstractHandler {

  private AbstractHandler next;

  public void handle(String target) {

    boolean isSuccess = doSomething(target);
    if (isSuccess) {
      return;
    } else {
      next.handle(target);
    }
  }

  public abstract boolean doSomething(String target);
}
