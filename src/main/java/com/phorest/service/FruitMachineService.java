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
      SpinResult spinResult = fruitMachine.spin(4);
      List<Colours> slots = spinResult.slots();

      WinStatus winStatus = determineWinStatus(slots);
      return new PlayResult(slots, winStatus);
   }

   private WinStatus determineWinStatus(List<Colours> slots) {
      boolean allMatch = slots.stream().distinct().count() == 1;
      if (allMatch) {
         return WinStatus.JACKPOT;
      }

      boolean allDifferent = slots.stream().distinct().count() == slots.size();
      if (allDifferent) {
         return WinStatus.FULL_HOUSE;
      }

      for (int i = 0; i < slots.size() - 1; i++) {
         if (slots.get(i) == slots.get(i + 1)) {
            return WinStatus.SMALL_WIN;
         }
      }

      return WinStatus.NO_WIN;
   }

}
