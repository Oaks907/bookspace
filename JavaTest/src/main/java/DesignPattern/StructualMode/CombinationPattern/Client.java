package DesignPattern.StructualMode.CombinationPattern;

/**
 * Create by haifei on 21/12/2018 5:26 PM.
 */
public class Client {

  public static void main(String[] args) {
    AbstractCombination root = new ConcreBranchA("BranchA");
    AbstractCombination leafA = new ConcreteLeafA("LeafA");
    AbstractCombination leafB = new ConcreteLeafB("LeafB");

    root.addMember(leafA);
    root.addMember(leafB);

    root.displayLevel();
  }
}
