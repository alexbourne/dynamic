package com.alexbourne.dynamic;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Fibonacci {

    private final Map<Long,Long> cache = new ConcurrentHashMap<>(32);

    /**
     * Calculate the fibonacci number for n using recursion
     *
     * @param n calculate the n-th fibonacci number
     * @return fib(n)
     */
    public long fibRecurse(long n) {
        if (n < 0) throw new IllegalArgumentException("Positive integers only please!");
        if (n < 2) return n;

        return fibRecurse(n - 1) + fibRecurse( n - 2);
    }


    /**
     * Do not use this as it may hang! It violates the contract with
     * computing other keys - see the api docs
     *
     * @param n calculate the n-th fibonacci number
     * @return fib(n)
     */
    @Deprecated
    public long fibCachingDodgy(long n) {
        if (n < 0) throw new IllegalArgumentException("Positive integers only please!");
        if (n < 2) return n;

        return cache.computeIfAbsent(n, key -> fibCachingDodgy(n - 1 ) + fibCachingDodgy(n - 2));
    }

    /**
     * Calculate the fibonacci number for n using dynamic programming
     *
     * @param n calculate the n-th fibonacci number
     * @return fib(n)
     */
    public long fibDynamic(int n) {
        long[] f = new long[n + 1];
        f[0] = 0;
        f[1] = 1;

        for (int i = 2; i <= n; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }

        return f[n];
    }

    /**
     * Calculate the fibonacci number for n using dynamic programming
     * with constant space
     *
     * @param n calculate the n-th fibonacci number
     * @return fib(n)
     */
    public long fibDynamicUltimate(int n) {
        if (n < 0) throw new IllegalArgumentException("Positive integers only please!");
        if (n < 2) return n;

        long back2 = 0, back1 = 1;
        long next;

        for (int i = 2; i < n; i++) {
            next = back1 + back2;
            back2 = back1;
            back1 = next;
        }

        return back1 + back2;
    }
}
