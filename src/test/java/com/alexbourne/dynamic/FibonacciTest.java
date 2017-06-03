package com.alexbourne.dynamic;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static org.junit.Assert.assertEquals;

public class FibonacciTest {

    private Fibonacci fibonacci;

    @Before
    public void setUp() {
        fibonacci = new Fibonacci();
    }

    @Test
    public void testFibWithRecursion() {
        assertEquals(55,fibonacci.fibRecurse(10));
    }

    @Test
    public void testFibDynamic() {
        assertEquals(55,fibonacci.fibDynamic(10));
    }

    @Test
    public void testFibDynamicUltimate() {
        assertEquals(55,fibonacci.fibDynamicUltimate(10));
    }

    @Test
    public void showTimingForEachApproach() {
        int testValue = 32;


        fibonacci = new Fibonacci();
        printTimeTaken("Recursion", fibonacci::fibRecurse, testValue);

        fibonacci = new Fibonacci();
        printTimeTaken("Dynamic  ", fibonacci::fibDynamic, testValue);

        fibonacci = new Fibonacci();
        printTimeTaken("Dynamic  ", fibonacci::fibDynamicUltimate, testValue);
    }

    private void printTimeTaken(String type, Function<Integer, Long> f, int value){
        long t0 = System.nanoTime();
        long result = f.apply(value);
        long t1 = System.nanoTime();

        System.out.println(String.format("%s took %d millis. Result: %d", type, TimeUnit.NANOSECONDS.toMillis(t1 - t0), result));
    }

}