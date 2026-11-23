package com.phorest.service;

import com.phorest.model.Colours;
import com.phorest.model.FruitMachine;
import com.phorest.model.PlayResult;
import com.phorest.model.SpinResult;
import com.phorest.model.WinStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FruitMachineServiceTests {

   private FruitMachineService fruitMachineService;
   private FruitMachine fruitMachine;

   @BeforeEach
   void setup() {
      fruitMachine = mock(FruitMachine.class);
      fruitMachineService = new FruitMachineService(fruitMachine);
   }

   @Test
   @DisplayName("Play Returns No Win Result If Not Small Win, Full House, or Jackpot")
   void playReturnsNoWinResultIfNotSmallWinFullHouseOrJackpot() {
      // Arrange
      when(fruitMachine.spin(4)).thenReturn(new SpinResult(List.of(Colours.BLACK, Colours.WHITE, Colours.GREEN, Colours.BLACK)));

      // Act
      PlayResult playResult = fruitMachineService.play();

      // Assert
      assertEquals(WinStatus.NO_WIN, playResult.winStatus());
      assertEquals(4, playResult.slots().size());
      assertEquals(List.of(Colours.BLACK, Colours.WHITE, Colours.GREEN, Colours.BLACK), playResult.slots());
   }

   @Test
   @DisplayName("Play Returns Jackpot Result If All Slots Match")
   void playReturnsJackpotResultIfAllSlotsMatch() {
      // Arrange
      when(fruitMachine.spin(4)).thenReturn(new SpinResult(List.of(Colours.BLACK, Colours.BLACK, Colours.BLACK, Colours.BLACK)));

      // Act
      PlayResult playResult = fruitMachineService.play();

      // Assert
      assertEquals(WinStatus.JACKPOT, playResult.winStatus());
      assertEquals(4, playResult.slots().size());
      assertEquals(List.of(Colours.BLACK, Colours.BLACK, Colours.BLACK, Colours.BLACK), playResult.slots());
   }

   @Test
   @DisplayName("Play Returns Full House Result If All Slots Are Different")
   void playReturnsFullHouseResultIfAllSlotsAreDifferent() {
      // Arrange
      when(fruitMachine.spin(4)).thenReturn(new SpinResult(List.of(Colours.BLACK, Colours.WHITE, Colours.GREEN, Colours.YELLOW)));

      // Act
      PlayResult playResult = fruitMachineService.play();

      // Assert
      assertEquals(WinStatus.FULL_HOUSE, playResult.winStatus());
      assertEquals(4, playResult.slots().size());
      assertEquals(List.of(Colours.BLACK, Colours.WHITE, Colours.GREEN, Colours.YELLOW), playResult.slots());
   }

   @Test
   @DisplayName("Play Returns Small Win Result If Two or More Adjacent Slots Match")
   void playReturnsSmallWinResultIfTwoOrMoreAdjacentSlotsMatch() {
      // Arrange
      when(fruitMachine.spin(4)).thenReturn(new SpinResult(List.of(Colours.BLACK, Colours.BLACK, Colours.GREEN, Colours.YELLOW)));

      // Act
      PlayResult playResult = fruitMachineService.play();

      // Assert
      assertEquals(WinStatus.SMALL_WIN, playResult.winStatus());
      assertEquals(4, playResult.slots().size());
      assertEquals(List.of(Colours.BLACK, Colours.BLACK, Colours.GREEN, Colours.YELLOW), playResult.slots());
   }


   // Add float


}
