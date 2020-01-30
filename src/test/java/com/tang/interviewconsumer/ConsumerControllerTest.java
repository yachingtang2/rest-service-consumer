package com.tang.interviewconsumer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ExtendWith(MockitoExtension.class)
class ConsumerControllerTest {

  private MockMvc mvc;

  @InjectMocks
  private ConsumerController controller;

  @Mock
  private ConsumerService service;

  @BeforeEach
  void setUp() {
    mvc = standaloneSetup(controller).build();
  }

  @Test
  void getRemoteMenu() throws Exception {
    mvc.perform(get("/getMenu")
        .accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk());
  }

  @Test
  void getDefaultFoodOrderCount() throws Exception {
    int count = 3;
    FoodOrderCount foodOrderCount = new FoodOrderCount("French Fries", count);

    given(service.getDefaultOrder(count)).willReturn(foodOrderCount);

    mvc.perform(get("/getDefaultFoodOrderCount?count=" + count)
        .accept(MediaType.APPLICATION_JSON))
      .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  void getDefaultFoodOrderCountBadRequest() throws Exception {
    mvc.perform(get("/getDefaultFoodOrderCount"))
        .andExpect(status().isBadRequest());
  }

  @Test
  void getFoodOrderCount() throws Exception {
    int count = 8;
    String banana = "banana";
    FoodOrderCount foodOrderCount = new FoodOrderCount(banana, count);

    given(service.getOrder(banana, count)).willReturn(foodOrderCount);

    mvc.perform(get("/getOrder?name=" + banana + "&count=" + count)
        .accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk());
  }

  @Test
  void getOrderBadRequest() throws Exception {
    mvc.perform(get("/getOrder"))
      .andExpect(status().isBadRequest());
  }
}
