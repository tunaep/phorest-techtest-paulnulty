package com.phorest.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "fruit-machine")
public class FruitMachineProperties {
   private int numSlots;
   private int currentFloat;
   private int freePlays;
   private int spinCost;

   public FruitMachineProperties(
         int numSlots,
         int currentFloat,
         int freePlays,
         int spinCost
   ) {
      this.numSlots = numSlots;
      this.currentFloat = currentFloat;
      this.freePlays = freePlays;
      this.spinCost = spinCost;
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

   public int getFreePlays() {
      return freePlays;
   }

   public void setFreePlays(int freePlays) {
      this.freePlays = freePlays;
   }

   public int getSpinCost() {
      return spinCost;
   }

   public void setSpinCost(int spinCost) {
      this.spinCost = spinCost;
   }
}
