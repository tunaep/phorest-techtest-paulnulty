package com.phorest.service;

import com.phorest.model.Colours;
import com.phorest.model.PlayResult;
import com.phorest.model.WinStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FruitMachineService {

   public PlayResult play() {
      // Implement the logic to play the fruit machine and return the result
      return new PlayResult(
            List.of(Colours.BLACK, Colours.WHITE, Colours.GREEN, Colours.YELLOW),
            WinStatus.NO_WIN
      );
   }

}
