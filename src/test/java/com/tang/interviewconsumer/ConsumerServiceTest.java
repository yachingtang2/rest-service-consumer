package com.tang.interviewconsumer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ConsumerServiceTest {

  @InjectMocks
  private ConsumerService service;

  @Mock
  private RestTemplate restTemplate;

  @Mock
  private FoodOrderTransformer transformer;

  private String frenchFries = "French Fries";

  private String URL = "http://localhost:8080/order?";

  private String defaultOrderUrl = URL + "count=";

  private String muffin = "muffin";

  private String orderUrl = URL + "value=" + muffin + "&count=";

  @Test
  void getDefaultOrder() {
    int count = 1;
    FoodOrder foodOrder = new FoodOrder(new Food(frenchFries), count);
    ResponseEntity<FoodOrder> responseEntity = new ResponseEntity<>(foodOrder, HttpStatus.OK);
    FoodOrderCount foodOrderCount = new FoodOrderCount(frenchFries, count);

    given(restTemplate.getForEntity(URI.create(defaultOrderUrl + count), FoodOrder.class)).willReturn(responseEntity);
    given(transformer.apply(foodOrder)).willReturn(foodOrderCount);

    assertThat(service.getDefaultOrder(count)).isEqualTo(foodOrderCount);

    verify(transformer, times(1)).apply(foodOrder);
  }

  @Test
  void getDefaultOrderNull() {
    int count = 2;
    FoodOrder foodOrder = new FoodOrder(new Food(frenchFries), count);
    ResponseEntity<FoodOrder> responseEntity = new ResponseEntity<>(foodOrder, HttpStatus.BAD_REQUEST);

    given(restTemplate.getForEntity(URI.create(defaultOrderUrl + count), FoodOrder.class)).willReturn(responseEntity);

    assertThat(service.getDefaultOrder(count)).isEqualTo(null);

    verify(transformer, times(0)).apply(foodOrder);
  }

  @Test
  void getOrder() {
    int count = 3;
    FoodOrder foodOrder = new FoodOrder(new Food(muffin), count);
    ResponseEntity<FoodOrder> responseEntity = new ResponseEntity<>(foodOrder, HttpStatus.OK);
    FoodOrderCount foodOrderCount = new FoodOrderCount(muffin, count);

    given(restTemplate.getForEntity(URI.create(orderUrl + count), FoodOrder.class)).willReturn(responseEntity);
    given(transformer.apply(foodOrder)).willReturn(foodOrderCount);

    assertThat(service.getOrder(muffin, count)).isEqualTo(foodOrderCount);

    verify(transformer, times(1)).apply(foodOrder);
  }

  @Test
  void getOrderNull() {
    int count = 2;
    FoodOrder foodOrder = new FoodOrder(new Food(muffin), count);
    ResponseEntity<FoodOrder> responseEntity = new ResponseEntity<>(foodOrder, HttpStatus.NOT_FOUND);

    given(restTemplate.getForEntity(URI.create(orderUrl + count), FoodOrder.class)).willReturn(responseEntity);

    assertThat(service.getOrder(muffin, count)).isEqualTo(null);

    verify(transformer, times(0)).apply(foodOrder);
  }
}
