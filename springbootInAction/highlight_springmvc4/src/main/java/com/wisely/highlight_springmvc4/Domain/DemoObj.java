package com.wisely.highlight_springmvc4.Domain;

/**
 * Create by haifei on 26/8/2018.
 */
public class DemoObj {

  private Long id;
  private String name;

  public DemoObj() {
    super();
  }

  public DemoObj(Long id, String name) {
    super();
    this.id = id;
    this.name = name;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
