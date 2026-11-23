package com.phorest.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "fruit-machine")
public class FruitMachineProperties {
   private int numSlots;
   private int currentFloat;
   private int spinsRemaining;
   private int spinCost;
   private int winnings;

   public FruitMachineProperties(
         int numSlots,
         int currentFloat,
         int spinsRemaining,
         int spinCost,
         int winnings
   ) {
      this.numSlots = numSlots;
      this.currentFloat = currentFloat;
      this.spinsRemaining = spinsRemaining;
      this.spinCost = spinCost;
      this.winnings = winnings;
   }

   public int getWinnings() {
      return winnings;
   }

   public void setWinnings(int winnings) {
      this.winnings = winnings;
   }

   public int getNumSlots() {
      return numSlots;
   }

   public void setNumSlots(int numSlots) {
      this.numSlots = numSlots;
   }

   public int getCurrentFloat() {
      return currentFloat;
   }

   public void setCurrentFloat(int currentFloat) {
      this.currentFloat = currentFloat;
   }

   public int getSpinsRemaining() {
      return spinsRemaining;
   }

   public void setSpinsRemaining(int spinsRemaining) {
      this.spinsRemaining = spinsRemaining;
   }

   public int getSpinCost() {
      return spinCost;
   }

   public void setSpinCost(int spinCost) {
      this.spinCost = spinCost;
   }
}
