package com.wisely.ch8_6_1_mongodb.domain;

/**
 * Create by haifei on 4/9/2018 7:06 AM.
 */
public class Location {

  private String place;
  private String year;

  public Location(String place, String year) {
    super();
    this.place = place;
    this.year = year;
  }

  public String getPlace() {
    return place;
  }

  public void setPlace(String place) {
    this.place = place;
  }

  public String getYear() {
    return year;
  }

  public void setYear(String year) {
    this.year = year;
  }
}