package DesignPattern.StructualMode.CombinationPattern;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by haifei on 21/12/2018 5:20 PM.
 */
public abstract class AbstractCombination {

  String name;

  public AbstractCombination(String name) {
    this.name = name;
  }

  List<AbstractCombination> list = new ArrayList<>();

  public void addMember(AbstractCombination concrete) {
    list.add(concrete);
  }

  public void displayLevel() {
    System.out.println("当前 Name：" + name);
    for (AbstractCombination item : list) {
      item.displayLevel();
    }
  }

}
