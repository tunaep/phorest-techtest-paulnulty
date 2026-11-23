package com.phorest.model;

import com.phorest.properties.FruitMachineProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FruitMachine {

   private FruitMachineProperties properties;

   public FruitMachine(FruitMachineProperties properties) {
      this.properties = properties;
   }

   public SpinResult spin() {
      if (properties.getNumSlots() <= 0) {
         throw new IllegalArgumentException("Number of slots must be greater than 0");
      }

      List<Colours> slots = Colours.getRandomColours(properties.getNumSlots());
      return new SpinResult(slots);
   }

   public FruitMachineProperties getProperties() {
      return properties;
   }

   public void setProperties(FruitMachineProperties properties) {
      this.properties = properties;
   }

}
