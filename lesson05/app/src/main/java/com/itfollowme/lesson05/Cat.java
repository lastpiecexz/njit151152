package com.itfollowme.lesson05;

/**
 * Created by notre on 2018/4/19.
 */

public class Cat {
  private int id;
  private String color;
  private String name;
  private String type;

  public Cat() {
  }

  public Cat(String type) {
    this.type = type;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}
