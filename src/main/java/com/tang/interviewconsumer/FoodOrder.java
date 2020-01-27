package com.tang.interviewconsumer;

import lombok.Value;

@Value
public class FoodOrder {

  private Food food;
  private int count;
}
