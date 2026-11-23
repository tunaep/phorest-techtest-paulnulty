package com.phorest.model;

import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class FruitMachine {

   public SpinResult spin() {
      return new SpinResult(
            List.of(Colours.BLACK, Colours.WHITE, Colours.GREEN, Colours.YELLOW)
      );
   }

}
