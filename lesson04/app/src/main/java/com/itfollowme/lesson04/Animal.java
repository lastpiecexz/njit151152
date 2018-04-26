package com.itfollowme.lesson04;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by notre on 2018/4/12.
 */

public class Animal implements Parcelable {

  int age;
  String name;

  public Animal() {
  }

  protected Animal(Parcel in) {
  }

  public static final Creator<Animal> CREATOR = new Creator<Animal>() {
    @Override
    public Animal createFromParcel(Parcel in) {
      Animal animal = new Animal();
      animal.name = in.readString();
      animal.age = in.readInt();
      return animal;
    }

    @Override
    public Animal[] newArray(int size) {
      return new Animal[size];
    }
  };

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(name);
    dest.writeInt(age);
  }
}
