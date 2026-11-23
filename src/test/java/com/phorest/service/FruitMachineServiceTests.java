package com.phorest.service;

import com.phorest.model.Colours;
import com.phorest.model.FruitMachine;
import com.phorest.model.PlayResult;
import com.phorest.model.SpinResult;
import com.phorest.model.WinStatus;
import com.phorest.properties.FruitMachineProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class FruitMachineServiceTests {

   private FruitMachineService fruitMachineService;
   private FruitMachine fruitMachine;
   private FruitMachineProperties properties;

   @BeforeEach
   void setup() {
      properties = new FruitMachineProperties(4, 1000, 100, 1, 0);
      FruitMachine fm = new FruitMachine(properties);
      fruitMachine = spy(fm);
      fruitMachineService = new FruitMachineService(fruitMachine);
   }

   // Can't spin unless there's enough spins remaining and enough float to cover the spin cost
   @Test
   @DisplayName("Play Throws IllegalStateException If Spin Credits Are Insufficient")
   void playThrowsIllegalStateExceptionIfSpinCreditsAreInsufficient() {
      // Arrange
      fruitMachine.getProperties().setSpinsRemaining(0);

      // Act
      // Assert
      assertThrows(IllegalStateException.class, () -> fruitMachineService.play());
   }


   @Test
   @DisplayName("Play Returns No Win Result If Not Small Win, Full House, or Jackpot")
   void playReturnsNoWinResultIfNotSmallWinFullHouseOrJackpot() {
      // Arrange
      when(fruitMachine.spin()).thenReturn(new SpinResult(List.of(Colours.BLACK, Colours.WHITE, Colours.GREEN, Colours.BLACK)));

      // Act
      PlayResult playResult = fruitMachineService.play();

      // Assert
      assertEquals(WinStatus.NO_WIN, playResult.winStatus());
      assertEquals(4, playResult.slots().size());
      assertEquals(List.of(Colours.BLACK, Colours.WHITE, Colours.GREEN, Colours.BLACK), playResult.slots());
      // When No Win, deduct spin cost from spinsRemaining
      assertEquals(99, playResult.spinsRemaining());
      assertEquals(1, playResult.spinCost());
      assertEquals(1000, playResult.currentFloat());
      assertEquals(0, playResult.winnings());
   }

   @Test
   @DisplayName("Play Returns Jackpot Result If All Slots Match")
   void playReturnsJackpotResultIfAllSlotsMatch() {
      // Arrange
      when(fruitMachine.spin()).thenReturn(new SpinResult(List.of(Colours.BLACK, Colours.BLACK, Colours.BLACK, Colours.BLACK)));

      // Act
      PlayResult playResult = fruitMachineService.play();

      // Assert
      assertEquals(WinStatus.JACKPOT, playResult.winStatus());
      assertEquals(4, playResult.slots().size());
      assertEquals(List.of(Colours.BLACK, Colours.BLACK, Colours.BLACK, Colours.BLACK), playResult.slots());
      assertEquals(0, playResult.currentFloat());
      assertEquals(1000, playResult.winnings());
   }

   @Test
   @DisplayName("Play Returns Full House Result If All Slots Are Different")
   void playReturnsFullHouseResultIfAllSlotsAreDifferent() {
      // Arrange
      when(fruitMachine.spin()).thenReturn(new SpinResult(List.of(Colours.BLACK, Colours.WHITE, Colours.GREEN, Colours.YELLOW)));

      // Act
      PlayResult playResult = fruitMachineService.play();

      // Assert
      assertEquals(WinStatus.FULL_HOUSE, playResult.winStatus());
      assertEquals(4, playResult.slots().size());
      assertEquals(List.of(Colours.BLACK, Colours.WHITE, Colours.GREEN, Colours.YELLOW), playResult.slots());
      // Full house pays half float
      assertEquals(500, playResult.currentFloat());
      assertEquals(500, playResult.winnings());
   }

   @Test
   @DisplayName("Play Returns Small Win Result If Two or More Adjacent Slots Match")
   void playReturnsSmallWinResultIfTwoOrMoreAdjacentSlotsMatch() {
      // Arrange
      when(fruitMachine.spin()).thenReturn(new SpinResult(List.of(Colours.BLACK, Colours.BLACK, Colours.GREEN, Colours.YELLOW)));

      // Act
      PlayResult playResult = fruitMachineService.play();

      // Assert
      assertEquals(WinStatus.SMALL_WIN, playResult.winStatus());
      assertEquals(4, playResult.slots().size());
      assertEquals(List.of(Colours.BLACK, Colours.BLACK, Colours.GREEN, Colours.YELLOW), playResult.slots());
      // Small win pays out x5 the spin cost.
      assertEquals(995, playResult.currentFloat());
      assertEquals(5, playResult.winnings());
   }

   @Test
   @DisplayName("Play Returns Small Win Result With Free Spins If Float Is Insufficient")
   void playReturnsSmallWinResultWithFreeSpinsIfFloatIsInsufficient() {
      // Arrange
      when(fruitMachine.spin()).thenReturn(new SpinResult(List.of(Colours.BLACK, Colours.BLACK, Colours.GREEN, Colours.YELLOW)));
      fruitMachine.getProperties().setCurrentFloat(3);

      // Act
      PlayResult playResult = fruitMachineService.play();

      // Assert
      assertEquals(WinStatus.SMALL_WIN, playResult.winStatus());
      assertEquals(4, playResult.slots().size());
      assertEquals(List.of(Colours.BLACK, Colours.BLACK, Colours.GREEN, Colours.YELLOW), playResult.slots());
      // Small win pays out x5 the spin cost, but float is insufficient
      assertEquals(0, playResult.currentFloat());
      assertEquals(3, playResult.winnings());
      assertEquals(102, playResult.spinsRemaining());
   }

}
