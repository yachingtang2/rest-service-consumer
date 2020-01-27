package com.tang.interviewconsumer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ExtendWith(MockitoExtension.class)
class ConsumerControllerTest {

  private MockMvc mvc;

  @InjectMocks
  private ConsumerController controller;

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

}
