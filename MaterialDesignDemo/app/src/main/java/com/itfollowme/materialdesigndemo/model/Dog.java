package com.itfollowme.materialdesigndemo.model;

import io.realm.RealmObject;

/**
 * Created by notre on 2018/6/7.
 */

public class Dog extends RealmObject {
  private String name;
  private int age;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }
}
