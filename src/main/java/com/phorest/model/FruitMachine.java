package com.phorest.model;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FruitMachine {

   public SpinResult spin(int numSlots) {
      if (numSlots <= 0) {
         throw new IllegalArgumentException("Number of slots must be greater than 0");
      }

      List<Colours> slots = Colours.getRandomColours(numSlots);
      return new SpinResult(slots);
   }

}
