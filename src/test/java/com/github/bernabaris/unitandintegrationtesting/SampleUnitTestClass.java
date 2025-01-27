package com.github.bernabaris.unitandintegrationtesting;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class SampleUnitTestClass {

    Calculator calculatorTest;

    @BeforeEach
    void setUp() {
        System.out.println("inside before each");
        this.calculatorTest = new Calculator();
    }

    @AfterEach
    void afterEach() {
        System.out.println("inside after each");
    }

    @BeforeAll
    static void beforeAll() {
        System.out.println("inside before all");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("inside after all");
    }

    @Test
    @DisplayName(value = "This test should return equals when add two number")
    public void should_returnEquals_when_addTwoNumbers(){
        // given
        int firstNumber = 10;
        int secondNumber = 20;
        int expectedNumber = 30;

        //when
        int result = calculatorTest.add(firstNumber, secondNumber);

        //then
        Assertions.assertEquals(expectedNumber, result);
    }

    @RepeatedTest(10)
    public void should_returnNotEquals_when_addTwoNumbers(){
        int firstNumber = 10;
        int secondNumber = 20;
        int expectedNumber = 40;

        int result = calculatorTest.add(firstNumber, secondNumber);

        Assertions.assertNotEquals(expectedNumber, result);
    }

    @ParameterizedTest
    @ValueSource(ints = {-10,1,0,20})
    public void should_returnZero_when_multiplyNumberWithZero(int givenNumber){

        int firstNumber = givenNumber;
        int secondNumber = 0;

        int result = calculatorTest.multiply(firstNumber, secondNumber);

        Assertions.assertTrue(result == 0);
    }

    @ParameterizedTest(name = "1st={0} 2nd={1}")
    @CsvSource({"-10,-1","-45,-6","-4,-12"})
    public void should_returnTrue_whenMultiplyTwoNegativeNumbers(int firstGivenNumber, int secondGivenNumber){
        int firstNumber = firstGivenNumber;
        int secondNumber = secondGivenNumber;

        int result = calculatorTest.multiply(firstNumber, secondNumber);

        Assertions.assertTrue(result > 0);
    }

    @Test
    public void should_throwException_whenDivideByZero(){
        int firstNumber = 10;
        int secondNumber = 0;

        Executable executable = () -> calculatorTest.divide(firstNumber, secondNumber);


        Assertions.assertThrows(ArithmeticException.class, executable);

    }

    @Test
    public void test(){
        System.out.println("inside test");
    }

    class Calculator {
        int add(int a, int b) {
            return a + b;
        }

        int multiply(int a, int b) {
            return a * b;
        }

        int divide(int a, int b) {
            return a / b;
        }
    }

}
