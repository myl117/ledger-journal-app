package com.example.sandbox.demo.model;

import java.util.Arrays;

public class User {
  private String name;
  private String rate; 

  public User(String name, String rate) {
    this.name = name;
    this.rate = rate;
  }

  public String getName() {
    return name;
  }

  public String getRate() {
    return rate;
  }

  String[] higherRates = {"engineer", "doctor", "scientist", "mathematician"};

  public Boolean isHigherRate(){
    return Arrays.asList(higherRates).contains(rate.toLowerCase());
  }
}
