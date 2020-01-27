package com.tang.interviewconsumer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class InterviewConsumerApplicationTest {

  private InterviewConsumerApplication application;

  @BeforeEach
  void setUp() {
    application = new InterviewConsumerApplication();
  }

  @Test
  void applicationNotNull() {
    assertThat(application).isNotNull();
  }
}