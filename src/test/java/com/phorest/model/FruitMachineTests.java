package com.phorest.model;

import com.phorest.properties.FruitMachineProperties;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FruitMachineTests {

   @ParameterizedTest
   @MethodSource("provideValidNumberOfSlots")
   @DisplayName("Fruit Machine Returns Valid Number Of Slots")
   void fruitMachineReturnsValidNumberOfSlots(FruitMachineProperties properties) {
      // Arrange
      FruitMachine fruitMachine = new FruitMachine(properties);

      // Act
      SpinResult spinResult = fruitMachine.spin();

      // Assert
      assertEquals(properties.getNumSlots(), spinResult.slots().size());
   }

   @ParameterizedTest
   @MethodSource("provideInvalidNumberOfSlots")
   @DisplayName("Spin Throws IllegalArgumentException If Number Of Slots Is Less Than 1")
   void spinThrowsIllegalArgumentExceptionIfNumberOfSlotsIsLessThan1(FruitMachineProperties properties) {
      // Arrange
      FruitMachine fruitMachine = new FruitMachine(properties);

      // Act
      // Assert
      Exception exception = assertThrows(IllegalArgumentException.class, fruitMachine::spin);
      assertEquals("Number of slots must be greater than 0", exception.getMessage());
   }

   private static Stream<Arguments> provideInvalidNumberOfSlots() {
      return Stream.of(
            Arguments.of(new FruitMachineProperties(0, 100, 0, 5, 0)),
            Arguments.of(new FruitMachineProperties(-1, 100, 0, 5, 0)),
            Arguments.of(new FruitMachineProperties(-10, 100, 0, 5, 0))
      );
   }

   private static Stream<Arguments> provideValidNumberOfSlots() {
      return Stream.of(
            Arguments.of(new FruitMachineProperties(1, 100, 0, 5, 0)),
            Arguments.of(new FruitMachineProperties(2, 100, 0, 5, 0)),
            Arguments.of(new FruitMachineProperties(3, 100, 0, 5, 0)),
            Arguments.of(new FruitMachineProperties(4, 100, 0, 5, 0)),
            Arguments.of(new FruitMachineProperties(5, 100, 0, 5, 0)),
            Arguments.of(new FruitMachineProperties(10, 100, 0, 5, 0)),
            Arguments.of(new FruitMachineProperties(100, 100, 0, 5, 0))
      );
   }

}
