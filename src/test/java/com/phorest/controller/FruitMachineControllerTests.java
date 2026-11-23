package com.phorest.controller;

import com.phorest.response.PlayResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FruitMachineController.class)
public class FruitMachineControllerTests {

   @Autowired
   private MockMvc mockMvc;

   @Test
   @DisplayName("Play Returns Valid Response")
   void playReturnsValidResponse() throws Exception {
      // Arrange
      PlayResponse expectedResponse = new PlayResponse(
            List.of("BLACK", "WHITE", "GREEN", "YELLOW"),
            "NO_WIN"
      );

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


}
