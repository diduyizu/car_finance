package com.carfinance;

import java.math.BigInteger;

/**
 * Created by jiangyin on 15-3-12.
 */
public class Test {

    public static void afda(String status) {
        BigInteger b = new BigInteger(status);
        long l = b.longValue();
        System.out.print(l);
    }

    public static void main(String args[]) {
        afda("-5");
    }
}
