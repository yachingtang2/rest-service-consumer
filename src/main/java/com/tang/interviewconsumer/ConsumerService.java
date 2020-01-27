package com.tang.interviewconsumer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Objects;

@Service
public class ConsumerService {

  private RestTemplate restTemplate;
  private FoodOrderTransformer transformer;

  public ConsumerService(RestTemplate restTemplate, FoodOrderTransformer transformer) {
    this.restTemplate = restTemplate;
    this.transformer = transformer;
  }

  public FoodOrderCount getDefaultOrder(int count) {
    ResponseEntity<FoodOrder> responseEntity = this.restTemplate.getForEntity(URI.create("http://localhost:8080/order" +
            "?count=" + count), FoodOrder.class);

    return responseEntity.getStatusCode() != HttpStatus.OK ? null : transformer.apply(Objects.requireNonNull(responseEntity.getBody()));
  }

  public FoodOrderCount getOrder(String muffin, int count) {
    ResponseEntity<FoodOrder> responseEntity =
        this.restTemplate.getForEntity(URI.create("http://localhost:8080/order?value=" + muffin +
        "&count=" + count), FoodOrder.class);

    return responseEntity.getStatusCode() != HttpStatus.OK ? null :
        transformer.apply(Objects.requireNonNull(responseEntity.getBody()));
  }
}
