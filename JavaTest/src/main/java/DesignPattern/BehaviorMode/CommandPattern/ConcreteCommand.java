package DesignPattern.BehaviorMode.CommandPattern;

import lombok.Setter;

/**
 * Create by haifei on 21/12/2018 3:00 PM.
 */
@Setter
public class ConcreteCommand extends AbstractCommand {

  private Receiver receiver;

  public ConcreteCommand(Receiver receiver) {
    this.receiver = receiver;
  }

  @Override
  public void execute() {
    receiver.receiver();
  }
}
