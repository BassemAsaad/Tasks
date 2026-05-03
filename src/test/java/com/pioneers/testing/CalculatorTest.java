package com.pioneers.testing;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RequiredArgsConstructor
public class CalculatorTest {
    private static Calculator calculator ;

    @BeforeAll
    static void setUp(){
        calculator = new Calculator();
    }

    @Test
    void testSumTwoPositiveNumbers() {
        // Arrange
        int num1 = 4, num2 = 2;

        // Action
        int result = calculator.sum(num1, num2);

        // Assert
        assertEquals(6, result);
    }

    @Test
    void testDivideTwoNumbers() {
        // Arrange
        int num1 = -4, num2 = 0;

        // Action
//        int result = calculator.divide(num1, num2);

        // Assert
//        assertEquals(2, result);
        assertThrows(ArithmeticException.class, () -> {
            calculator.divide(num1, num2);
        });
    }
}
