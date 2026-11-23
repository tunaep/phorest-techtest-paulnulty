package com.phorest.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class FruitMachineControllerIntegrationTests {

   @Autowired
   private MockMvc mockMvc;

   @Test
   @DisplayName("Integration Test for Fruit Machine Controller")
   void integrationTestForFruitMachineController() throws Exception {
      // Arrange
      // Act
      // Assert
      mockMvc.perform(post("/api/v1/fruit-machine/play"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.slots").isArray())
            .andExpect(jsonPath("$.slots.length()").value(4))
            .andExpect(jsonPath("$.result").isString());
   }


}
