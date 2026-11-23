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

      // Update the float and free plays based on the win status
      updateFruitMachineStatus(winStatus);

      return new PlayResult(
            slots,
            winStatus,
            fruitMachine.getProperties().getCurrentFloat(),
            fruitMachine.getProperties().getFreePlays(),
            fruitMachine.getProperties().getSpinCost()
      );
   }

   private void updateFruitMachineStatus(WinStatus winStatus) {
      if (winStatus == WinStatus.NO_WIN) {
         // Deduct the spin cost from the float
         int newFloat = fruitMachine.getProperties().getCurrentFloat() - fruitMachine.getProperties().getSpinCost();
         fruitMachine.getProperties().setCurrentFloat(newFloat);
      } else if (winStatus == WinStatus.SMALL_WIN) {
         // For a small win, we can decide to not deduct from the float or give a small payout
         // Here, we will not deduct from the float
      } else if (winStatus == WinStatus.FULL_HOUSE) {
         // For a full house, we can decide to give a larger payout
         // Here, we will not deduct from the float
      } else if (winStatus == WinStatus.JACKPOT) {
         // For a jackpot, we can decide to give the largest payout
         // Here, we will not deduct from the float
      }
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
