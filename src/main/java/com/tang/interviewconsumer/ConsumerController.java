package com.tang.interviewconsumer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {

  @GetMapping("/getMenu")
  public ResponseEntity<FoodMenu> getMenu() {
    return new ResponseEntity<>(null, HttpStatus.OK);
  }
}
