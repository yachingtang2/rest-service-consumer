package com.tang.interviewconsumer;

import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class FoodOrderTransformer implements Function<FoodOrder, FoodOrderCount> {

  @Override
  public FoodOrderCount apply(FoodOrder foodOrder) {
    return new FoodOrderCount(foodOrder.getFood().getName(), foodOrder.getCount());
  }
}
