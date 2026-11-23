package com.phorest.controller;

import com.phorest.mapper.PlayResponseMapper;
import com.phorest.model.PlayResult;
import com.phorest.response.PlayResponse;
import com.phorest.service.FruitMachineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/fruit-machine")
public class FruitMachineController {

   private final FruitMachineService fruitMachineService;
   private final PlayResponseMapper playResponseMapper;

   public FruitMachineController(FruitMachineService fruitMachineService, PlayResponseMapper playResponseMapper) {
      this.fruitMachineService = fruitMachineService;
      this.playResponseMapper = playResponseMapper;
   }

   @PostMapping("/play")
   public ResponseEntity<PlayResponse> play() {
      PlayResult playResult = fruitMachineService.play();

      PlayResponse response = playResponseMapper.fromPlayResult(playResult);

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

