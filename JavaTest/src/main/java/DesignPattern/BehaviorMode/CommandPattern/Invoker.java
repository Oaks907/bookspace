package DesignPattern.BehaviorMode.CommandPattern;

import lombok.Data;

/**
 * Create by haifei on 21/12/2018 2:59 PM.
 */
@Data
public class Invoker {

  AbstractCommand command;

  public void action() {
    command.execute();
  }
}
