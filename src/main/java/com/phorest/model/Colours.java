package com.phorest.model;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public enum Colours {
   BLACK("black"),
   WHITE("white"),
   GREEN("green"),
   YELLOW("yellow");

   private final String colour;

   Colours(String colour) {
      this.colour = colour;
   }

   public static List<Colours> getRandomColours(int numSlots) {
      return ThreadLocalRandom.current()
            .ints(numSlots, 0, Colours.values().length)
            .mapToObj(i -> Colours.values()[i])
            .toList();
   }

   public String getColour() {
      return colour;
   }
}
