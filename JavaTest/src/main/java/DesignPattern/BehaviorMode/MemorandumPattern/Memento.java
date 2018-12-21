package DesignPattern.BehaviorMode.MemorandumPattern;

import lombok.Data;

/**
 * Create by haifei on 21/12/2018 3:20 PM.
 */
@Data
public class Memento {

  String state;

  public Memento(String state) {
    this.state = state;
  }
}
