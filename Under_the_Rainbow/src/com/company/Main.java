package com.company;

import java.io.IOException;
import java.io.InputStream;
import java.util.NoSuchElementException;

public class Main {

    static int line;
    static long penalties[];
    static long distance[];
    static long max = Long.MAX_VALUE;


    public static void main(String[] args) {
        FastScanner scan = new FastScanner();

        line = Integer.parseInt(scan.next());
        int num = line + 1;
        distance = new long[num];
        penalties = new long[num];

        for(int i = 0; i < num; i++){
            distance[i] = Integer.parseInt(scan.next());
        }

        System.out.println(penalty(0));
    }

    static long penalty(int n){
        calculate_penalty();
        return penalties[n];
    }

    /**
     * calculate minimum penalties and store in
     */
    static void calculate_penalty(){
        for(int i = line; i >= 0; i--){
            if(i == line){
                penalties[i] = 0;
                continue;
            }
            else{
                long min = max;
                for(int j = i+1; j<= line; j++){
                    long temp = penalties[j] + calculate_penalty_from_to(i,j);
                    if(min > temp)
                        min = temp;
                }
                penalties[i] = min;
            }
        }
    }

    static long calculate_penalty_from_to(int i, int n){
        long a = 400 - distance[n] + distance[i];
        return a*a;
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