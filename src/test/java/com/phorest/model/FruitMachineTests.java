package com.phorest.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FruitMachineTests {

   private FruitMachine fruitMachine;

   @BeforeEach
   void setup() {
      fruitMachine = new FruitMachine();
   }

   @ParameterizedTest
   @MethodSource("provideValidNumberOfSlots")
   @DisplayName("Fruit Machine Returns Valid Number Of Slots")
   void fruitMachineReturnsValidNumberOfSlots(int numSlots) {
      // Arrange
      // Act
      SpinResult spinResult = fruitMachine.spin(numSlots);

      // Assert
      assertEquals(numSlots, spinResult.slots().size());
   }

   @ParameterizedTest
   @MethodSource("provideInvalidNumberOfSlots")
   @DisplayName("Spin Throws IllegalArgumentException If Number Of Slots Is Less Than 1")
   void spinThrowsIllegalArgumentExceptionIfNumberOfSlotsIsLessThan1(int numSlots) {
      // Arrange
      // Act
      // Assert
      Exception exception = assertThrows(IllegalArgumentException.class, () -> fruitMachine.spin(numSlots));
      assertEquals("Number of slots must be greater than 0", exception.getMessage());
   }

   private static Stream<Arguments> provideInvalidNumberOfSlots() {
      return Stream.of(
            Arguments.of(0),
            Arguments.of(-1),
            Arguments.of(-10)
      );
   }

   private static Stream<Arguments> provideValidNumberOfSlots() {
      return Stream.of(
            Arguments.of(1),
            Arguments.of(2),
            Arguments.of(3),
            Arguments.of(4),
            Arguments.of(5),
            Arguments.of(10),
            Arguments.of(100)
      );
   }

}
