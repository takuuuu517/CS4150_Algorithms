package com.company;


import java.io.IOException;
import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    static String operation;
    static long num1;
    static long num2;
    static long num3;

    static long inverse;

    public static void main(String[] args) {
        // write your code here

        FastScanner scan = new FastScanner();
        while (scan.hasNext()) {

            operation = scan.next();
            num1 = scan.nextInt();

            if (!operation.equals("isprime"))
                num2 = scan.nextInt();

            if (operation.equals("exp")) {
                num3 = scan.nextInt();
            }

            compute(operation);
        }
    }

    static long mod(long a, long b) {
        if (a < 0) {
            long s = (a*(-1)) / b + 1;
            long t = a + b * s;

            return t;
        } else {
            return a % b;
        }
    }


    static void compute(String operation) {
        switch (operation) {
            case "gcd":
                gcd();
                break;
            case "exp":
                exp();
                break;
            case "inverse":
                inverse();
                break;
            case "isprime":
                isprime();
                break;
            case "key":
                key();
                break;
            default:
                break;
        }
    }


    // 2
    static void gcd() {
        long a = num1;
        long b = num2;

        System.out.println(gcd(a, b));
    }

    static long gcd(long a, long b) {
        if (b == 0)
            return a;
        else
            return gcd(b, mod(a, b));
    }

    // 3
    static void exp() {
        long x = num1;
        long y = num2;
        long n = num3;

        System.out.println(exp(x, y, n));


    }

    static long exp(long x, long y, long N) {
        if (y == 0)
            return 1;
        else {
            long z = exp(x, y / 2, N);
            if (mod(y, 2) == 0) //y is even
                return mod(mod(z,N) * mod(z, N), N);
            else
                return mod(mod(mod(x,N) * mod(z,N), N) * mod(z, N), N);
        }
    }


    //2
    static void inverse() {
        long a = num1;
        long n = num2;


        if (inverse(a, n))
            System.out.println(inverse);
        else
            System.out.println("none");
    }

    static boolean inverse(long a, long N) {
        long[] array = ee(a, N);
        if (array[2] == 1) {
            inverse = mod(array[0], N);
            return true;
        } else
            return false;
    }

    static long[] ee(long a, long b) {
        if (b == 0)
            return new long[]{1, 0, a};

        else {
            long[] array = ee(b, mod(a, b));
            return new long[]{array[1], array[0] - (a / b) * array[1], array[2]};
        }
    }

    //1
    static void isprime() {
        long p = num1;
        long pminusone = num1 - 1;
        if (p < 101) { // check everything
            for (int a = 2; a < p; a++) {
                if (exp(a, pminusone, p) != 1) {
                    System.out.println("no");
                    return;
                }
            }
            System.out.println("yes");
            return;
        } else { // check 100
            ThreadLocalRandom r = ThreadLocalRandom.current();
            long randomNum;

            for (int i = 0; i < 100; i++) {
                randomNum = r.nextLong(2, p);
                if (exp(randomNum, pminusone, p) != 1) {
                    System.out.println("no");
                    return;
                }
            }
            System.out.println("yes");
            return;
        }
    }


    // 2
    static void key() {
        long p = num1;
        long q = num2;

        long N = p * q;
        long phi = (p - 1) * (q - 1);
        long e = -1;
        long d = -1;

        for (int i = 2; i < phi; i++) {
            if (gcd(i, phi) == 1) {
                e = i;
                break;
            }
        }
        if (inverse(e, phi))
            d = inverse;


        StringBuilder sb = new StringBuilder();

        sb.append(N).append(" ").append(e).append(" ").append(d);

        System.out.println(sb.toString());
    }
}


// Reference: https://qiita.com/p_shiki37/items/a0f6aac33bf60f5f65e4
// scanner that is faster than java scanner
class FastScanner {
    private final InputStream in = System.in;
    private final byte[] buffer = new byte[1024];
    private int ptr = 0;
    private int buflen = 0;
    private boolean hasNextByte() {
        if (ptr < buflen) {
            return true;
        }else{
            ptr = 0;
            try {
                buflen = in.read(buffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (buflen <= 0) {
                return false;
            }
        }
        return true;
    }
    private int readByte() { if (hasNextByte()) return buffer[ptr++]; else return -1;}
    private static boolean isPrintableChar(int c) { return 33 <= c && c <= 126;}
    public boolean hasNext() { while(hasNextByte() && !isPrintableChar(buffer[ptr])) ptr++; return hasNextByte();}
    public String next() {
        if (!hasNext()) throw new NoSuchElementException();
        StringBuilder sb = new StringBuilder();
        int b = readByte();
        while(isPrintableChar(b)) {
            sb.appendCodePoint(b);
            b = readByte();
        }
        return sb.toString();
    }
    public long nextLong() {
        if (!hasNext()) throw new NoSuchElementException();
        long n = 0;
        boolean minus = false;
        int b = readByte();
        if (b == '-') {
            minus = true;
            b = readByte();
        }
        if (b < '0' || '9' < b) {
            throw new NumberFormatException();
        }
        while(true){
            if ('0' <= b && b <= '9') {
                n *= 10;
                n += b - '0';
            }else if(b == -1 || !isPrintableChar(b)){
                return minus ? -n : n;
            }else{
                throw new NumberFormatException();
            }
            b = readByte();
        }
    }
    public int nextInt() {
        long nl = nextLong();
        if (nl < Integer.MIN_VALUE || nl > Integer.MAX_VALUE) throw new NumberFormatException();
        return (int) nl;
    }
    public double nextDouble() { return Double.parseDouble(next());}
}