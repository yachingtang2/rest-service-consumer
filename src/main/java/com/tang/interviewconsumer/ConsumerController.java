package com.tang.interviewconsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {

  @Autowired
  private ConsumerService service;

  @GetMapping("/getMenu")
  public ResponseEntity<FoodMenu> getMenu() {
    return new ResponseEntity<>(null, HttpStatus.OK);
  }

  @GetMapping(value = "/getDefaultFoodOrderCount", produces=MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<FoodOrderCount> getDefaultFoodOrderCount(@RequestParam int count) {

    FoodOrderCount foodOrderCount = service.getDefaultOrder(count);

    return new ResponseEntity<>(foodOrderCount, HttpStatus.OK);
  }

  @GetMapping(value = "/getOrder", produces=MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<FoodOrderCount> getOrder(@RequestParam String name, @RequestParam int count) {

    FoodOrderCount foodOrderCount = service.getOrder(name, count);

    return new ResponseEntity<FoodOrderCount>(foodOrderCount, HttpStatus.OK);
  }
}
