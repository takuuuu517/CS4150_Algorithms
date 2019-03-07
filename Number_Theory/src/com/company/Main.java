package com.company;

import java.io.IOException;
import java.io.InputStream;
import java.util.NoSuchElementException;

public class Main {

    static String operation;
    static int num1;
    static int num2;
    static int num3;

    public static void main(String[] args) {
	// write your code here

        FastScanner scan = new FastScanner();
        while (scan.hasNext())
        {
            int[] array;

            operation = scan.next();
            num1 = scan.nextInt();

            if(!operation.equals("isprime"))
                num2 = scan.nextInt();

            if (operation.equals("exp")) {
                num3 = scan.nextInt();
                System.out.println(operation + " " + num1 + " " + num2+" " + num3);
            }
            else
            System.out.println(operation + " " + num1 + " " + num2);


            array = createarray(operation);
            compute(operation, array);
        }
    }


    static int[] createarray(String operation){
        int array[];
        if(operation.equals("isprime")){
            array = new int[1];
            array[0] = num1;
        }
        else if (operation.equals("exp")){
            array = new int[3];
            array[0] = num1;
            array[1] = num2;
            array[2] = num3;
        }
        else{
            array = new int[2];
            array[0] = num1;
            array[1] = num2;
        }
        return array;
    }


    static void compute(String operation, int[] array){
        switch (operation){
            case "gcd":
                gcd(array);
                break;
            case "exp":
                exp(array);
                break;
            case "inverse":
                inverse(array);
                break;
            case "isprime":
                isprime(array);
                break;
            case "key":
                key(array);
                break;
            default:
                break;


        }
    }


    // 2
    static void gcd(int[] array){

    }

    // 3
    static void exp(int[] array){

    }

    //2
    static void inverse(int[] array){

    }

    //1
    static void isprime(int[] array){

    }

    // 2
    static void key(int[] array){

    }




}




// Reference: https://qiita.com/p_shiki37/items/a0f6aac33bf60f5f65e4
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