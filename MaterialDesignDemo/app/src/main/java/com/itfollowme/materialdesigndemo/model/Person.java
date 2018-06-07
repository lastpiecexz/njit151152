package com.itfollowme.materialdesigndemo.model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by notre on 2018/6/7.
 */

public class Person extends RealmObject {
  @PrimaryKey
  private long id;
  private String name;
  private RealmList<Dog> dogs; // Declare one-to-many relationships

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public RealmList<Dog> getDogs() {
    return dogs;
  }

  public void setDogs(RealmList<Dog> dogs) {
    this.dogs = dogs;
  }
}
