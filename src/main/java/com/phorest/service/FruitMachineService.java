package com.phorest.service;

import com.phorest.model.Colours;
import com.phorest.model.FruitMachine;
import com.phorest.model.PlayResult;
import com.phorest.model.SpinResult;
import com.phorest.model.WinStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FruitMachineService {

   private final FruitMachine fruitMachine;

   public FruitMachineService(FruitMachine fruitMachine) {
      this.fruitMachine = fruitMachine;
   }

   public PlayResult play() {
      SpinResult spinResult = fruitMachine.spin();
      List<Colours> slots = spinResult.slots();

      WinStatus winStatus = determineWinStatus(slots);
      return new PlayResult(slots, winStatus);
   }

   private WinStatus determineWinStatus(List<Colours> slots) {
      boolean allMatch = slots.stream().distinct().count() == 1;
      if (allMatch) {
         return WinStatus.JACKPOT;
      } else {
         return WinStatus.NO_WIN;
      }
   }

}
