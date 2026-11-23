package com.phorest.controller;

import com.phorest.response.PlayResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fruit-machine")
public class FruitMachineController {

   @PostMapping("/play")
   public ResponseEntity<PlayResponse> play() {
      // Implement the logic to play the fruit machine and return the result
      PlayResponse response = new PlayResponse(
            List.of("BLACK", "WHITE", "GREEN", "YELLOW"),
            "NO_WIN"
      );
      // Set the response properties based on the game logic
      return ResponseEntity.ok(response);
   }

   @GetMapping("/config")
   public ResponseEntity<String> getConfig() {
      throw new UnsupportedOperationException("Not implemented yet");
   }

   // Set config
   @PostMapping("/config")
   public ResponseEntity<String> setConfig() {
      throw new UnsupportedOperationException("Not implemented yet");
   }

}

