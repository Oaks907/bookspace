package com.wisely.highlight_spring4.ch3.conditional;

/**
 * Create by haifei on 25/8/2018.
 */
public class MacListService implements ListService{
  @Override
  public String showListCmd() {
    return "ls";
  }
}
