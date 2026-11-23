package com.phorest.controller;

import com.phorest.response.PlayResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FruitMachineController.class)
public class FruitMachineControllerUnitTests {

   @Autowired
   private MockMvc mockMvc;

   @MockitoBean
   private FruitMachineController fruitMachineController;

   @Test
   @DisplayName("Play Returns Valid Response If No Win")
   void playReturnsValidResponseIfNoWin() throws Exception {
      // Arrange
      PlayResponse expectedResponse = new PlayResponse(
            List.of("BLACK", "WHITE", "GREEN", "YELLOW"),
            "NO_WIN",
            100,
            0,
            1
      );

      when(fruitMachineController.play()).thenReturn(ResponseEntity.ok(expectedResponse));

      // Act
      // Assert
      mockMvc.perform(post("/api/v1/fruit-machine/play"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.slots").isArray())
            .andExpect(jsonPath("$.slots[0]").value("BLACK"))
            .andExpect(jsonPath("$.slots[1]").value("WHITE"))
            .andExpect(jsonPath("$.slots[2]").value("GREEN"))
            .andExpect(jsonPath("$.slots[3]").value("YELLOW"))
            .andExpect(jsonPath("$.result").value("NO_WIN"));
   }

   @Test
   @DisplayName("Play Returns Valid Response If Jackpot")
   void playReturnsValidResponseIfJackpot() throws Exception {
      // Arrange
      PlayResponse expectedResponse = new PlayResponse(
            List.of("BLACK", "BLACK", "BLACK", "BLACK"),
            "JACKPOT",
            100,
            0,
            1
      );

      when(fruitMachineController.play()).thenReturn(ResponseEntity.ok(expectedResponse));

      // Act
      // Assert
      mockMvc.perform(post("/api/v1/fruit-machine/play"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.slots").isArray())
            .andExpect(jsonPath("$.slots[0]").value("BLACK"))
            .andExpect(jsonPath("$.slots[1]").value("BLACK"))
            .andExpect(jsonPath("$.slots[2]").value("BLACK"))
            .andExpect(jsonPath("$.slots[3]").value("BLACK"))
            .andExpect(jsonPath("$.result").value("JACKPOT"));
   }

   @Test
   @DisplayName("Play Returns Valid Response If Small Win")
   void playReturnsValidResponseIfSmallWin() throws Exception {
      // Arrange
      PlayResponse expectedResponse = new PlayResponse(
            List.of("BLACK", "BLACK", "WHITE", "YELLOW"),
            "SMALL_WIN",
            100,
            0,
            1
      );

      when(fruitMachineController.play()).thenReturn(ResponseEntity.ok(expectedResponse));

      // Act
      // Assert
      mockMvc.perform(post("/api/v1/fruit-machine/play"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.slots").isArray())
            .andExpect(jsonPath("$.slots[0]").value("BLACK"))
            .andExpect(jsonPath("$.slots[1]").value("BLACK"))
            .andExpect(jsonPath("$.slots[2]").value("WHITE"))
            .andExpect(jsonPath("$.slots[3]").value("YELLOW"))
            .andExpect(jsonPath("$.result").value("SMALL_WIN"));
   }


   @Test
   @DisplayName("Default Error Message Is Received If Something Goes Wrong")
   void defaultErrorMessageIsReceivedIfSomethingGoesWrong() throws Exception {
      // Arrange
      when(fruitMachineController.play()).thenThrow(new RuntimeException("Something went wrong"));

      // Act
      // Assert
      mockMvc.perform(post("/api/v1/fruit-machine/play"))
            .andExpect(status().isInternalServerError())
            .andExpect(jsonPath("$.message").value("An unexpected error occurred"))
            .andExpect(jsonPath("$.timestamp").exists());
   }


}
