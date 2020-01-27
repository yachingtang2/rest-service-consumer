package com.tang.interviewconsumer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FoodOrderTransformerTest {

  private FoodOrderTransformer transformer;

  private Food food;

  private FoodOrder foodOrder;

  @BeforeEach
  void setUp() {
    transformer = new FoodOrderTransformer();
    food = new Food("coke");
    foodOrder = new FoodOrder(food, 5);
  }

  @Test
  void apply() {
    FoodOrderCount foodOrderCount = transformer.apply(foodOrder);

    assertThat(foodOrderCount.getName()).isEqualTo("coke");
    assertThat(foodOrderCount.getCount()).isEqualTo(5);
  }
}
