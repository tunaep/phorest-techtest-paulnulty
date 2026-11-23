package com.phorest.controller;

import com.phorest.model.Colours;
import com.phorest.model.PlayResult;
import com.phorest.model.WinStatus;
import com.phorest.service.FruitMachineService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class FruitMachineControllerIntegrationTests {

   @Autowired
   private MockMvc mockMvc;

   @MockitoSpyBean
   private FruitMachineService fruitMachineService;

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

   @Test
   @DisplayName("Integration Test for Fruit Machine Controller - No Win")
   void integrationTestForFruitMachineControllerNoWin() throws Exception {
      // Arrange
      when(fruitMachineService.play()).thenReturn(new PlayResult(List.of(Colours.BLACK, Colours.WHITE, Colours.GREEN, Colours.BLACK), WinStatus.NO_WIN));

      // Act
      // Assert
      mockMvc.perform(post("/api/v1/fruit-machine/play"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.slots").isArray())
            .andExpect(jsonPath("$.slots.length()").value(4))
            .andExpect(jsonPath("$.result").value("NO_WIN"));
   }

   @Test
   @DisplayName("Integration Test for Fruit Machine Controller - Jackpot")
   void integrationTestForFruitMachineControllerJackpot() throws Exception {
      // Arrange
      when(fruitMachineService.play()).thenReturn(new PlayResult(List.of(Colours.BLACK, Colours.BLACK, Colours.BLACK, Colours.BLACK), WinStatus.JACKPOT));

      // Act
      // Assert
      mockMvc.perform(post("/api/v1/fruit-machine/play"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.slots").isArray())
            .andExpect(jsonPath("$.slots.length()").value(4))
            .andExpect(jsonPath("$.result").value("JACKPOT"));
   }

   @Test
   @DisplayName("Integration Test for Fruit Machine Controller - Full House")
   void integrationTestForFruitMachineControllerFullHouse() throws Exception {
      // Arrange
      when(fruitMachineService.play()).thenReturn(new PlayResult(List.of(Colours.BLACK, Colours.WHITE, Colours.GREEN, Colours.YELLOW), WinStatus.FULL_HOUSE));

      // Act
      // Assert
      mockMvc.perform(post("/api/v1/fruit-machine/play"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.slots").isArray())
            .andExpect(jsonPath("$.slots.length()").value(4))
            .andExpect(jsonPath("$.result").value("FULL_HOUSE"));
   }

   @Test
   @DisplayName("Integration Test for Fruit Machine Controller - Small Win")
   void integrationTestForFruitMachineControllerSmallWin() throws Exception {
      // Arrange
      when(fruitMachineService.play()).thenReturn(new PlayResult(List.of(Colours.BLACK, Colours.BLACK, Colours.GREEN, Colours.YELLOW), WinStatus.SMALL_WIN));

      // Act
      // Assert
      mockMvc.perform(post("/api/v1/fruit-machine/play"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.slots").isArray())
            .andExpect(jsonPath("$.slots.length()").value(4))
            .andExpect(jsonPath("$.result").value("SMALL_WIN"));
   }

   @Test
   @DisplayName("Default Error Message Is Received If Something Goes Wrong")
   void defaultErrorMessageIsReceivedIfSomethingGoesWrong() throws Exception {
      // Arrange
      when(fruitMachineService.play()).thenThrow(new RuntimeException("Simulated error"));

      // Act
      // Assert
      mockMvc.perform(post("/api/v1/fruit-machine/play"))
            .andExpect(status().isInternalServerError())
            .andExpect(jsonPath("$.message").value("An unexpected error occurred"))
            .andExpect(jsonPath("$.timestamp").exists());
   }


}
