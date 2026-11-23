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
      if (fruitMachine.getProperties().getSpinsRemaining() - fruitMachine.getProperties().getSpinCost() < 0) {
         throw new IllegalStateException("Insufficient spins remaining.");
      }

      SpinResult spinResult = fruitMachine.spin();
      List<Colours> slots = spinResult.slots();

      WinStatus winStatus = determineWinStatus(slots);

      updateFruitMachineStatus(winStatus);

      return new PlayResult(
            slots,
            winStatus,
            fruitMachine.getProperties().getCurrentFloat(),
            fruitMachine.getProperties().getSpinsRemaining(),
            fruitMachine.getProperties().getSpinCost(),
            fruitMachine.getProperties().getWinnings()
      );
   }

   private void updateFruitMachineStatus(WinStatus winStatus) {
      if (winStatus == WinStatus.NO_WIN) {
         // Deduct the spin cost from the spins remaining
         fruitMachine.removeCredits(fruitMachine.getProperties().getSpinCost());
      } else if (winStatus == WinStatus.SMALL_WIN) {
         // Small win pays out x5 the spin cost. If not enough float to cover the payout, pay out the remaining float
         // and give rest in free spins
         int payout = fruitMachine.getProperties().getSpinCost() * 5;

         if (payout > fruitMachine.getProperties().getCurrentFloat()) {
            // If this is the case then we have to add the float to winnings, then pay the rest in free spins
            int remainingPayout = payout - fruitMachine.getProperties().getCurrentFloat();
            fruitMachine.getProperties().setWinnings(fruitMachine.getProperties().getWinnings() + fruitMachine.getProperties().getCurrentFloat());
            fruitMachine.getProperties().setCurrentFloat(0);

            // How much do we have to add to free spins?
            fruitMachine.getProperties().setSpinsRemaining(fruitMachine.getProperties().getSpinsRemaining() + remainingPayout);
         } else {
            fruitMachine.getProperties().setCurrentFloat(fruitMachine.getProperties().getCurrentFloat() - payout);
            fruitMachine.getProperties().setWinnings(fruitMachine.getProperties().getWinnings() + payout);
         }

      } else if (winStatus == WinStatus.FULL_HOUSE) {
         // TODO: Case where there's not enough float to cover payout is not covered.
         // Full house pays out half the float.
         int payout = fruitMachine.getProperties().getCurrentFloat() / 2;

         fruitMachine.getProperties().setCurrentFloat(fruitMachine.getProperties().getCurrentFloat() - payout);
         fruitMachine.getProperties().setWinnings(fruitMachine.getProperties().getWinnings() + payout);
      } else if (winStatus == WinStatus.JACKPOT) {
         // Jackpot pays out the entire float. Free spins doesn't apply here
         int payout = fruitMachine.getProperties().getCurrentFloat();
         fruitMachine.getProperties().setCurrentFloat(0);
         fruitMachine.getProperties().setWinnings(fruitMachine.getProperties().getWinnings() + payout);
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
