package com.company;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class Main {

    static int row;
    static int k;

    static int[][] values;

    static HashMap<String, Integer> caches;

    public static void main(String[] args) {
        caches = new HashMap<>();


//        HashMap<String , Integer> ab = new HashMap<>();

//        ab.put("hello", 111);
//        System.out.println(ab.get("hello"));

        FastScanner scan = new FastScanner();

        row = scan.nextInt();
        k = scan.nextInt();
        values = new int[row][2];

        for(int i = 0; i < row; i++){
            values[i][0] = scan.nextInt();
            values[i][1] = scan.nextInt();
        }

        System.out.println(maxValue(0, -1, k));
        System.out.println();
    }

    static int maxValue(int r, int uncloseableRoom, int k){
//        int[] arr = {r, uncloseableRoom, k}; // 0: row, 1: uncloseableRoom, 2: k
//        int cache = Arrays.hashCode(arr);
        String cache = r + " " + uncloseableRoom + " " + k;
//        int cache = r*10000 + uncloseableRoom * 100 + k;

        if(row == r)
            return 0;

        if(caches.containsKey(cache))
            return caches.get(cache);
        if(k == row - r){
            int a;
            switch (uncloseableRoom){
                case 0:
                    a = values[r][0]+maxValue(r+1,0,k-1);
                    caches.put(cache, a);
                    return a;
                case 1:
                    a= values[r][1]+maxValue(r+1,1,k-1);
                    caches.put(cache, a);
                    return a;
                case -1:
                    a = Math.max(values[r][0]+maxValue(r+1,0,k-1),values[r][1]+maxValue(r+1,1,k-1) );
                    caches.put(cache, a);
                    return a;
                default:
                    return 0;
            }
        }
        else{
            int a;
            switch (uncloseableRoom){
                case 0:
                    a = Math.max(values[r][0]+maxValue(r+1,0,k-1), values[r][1]+values[r][0]+maxValue(r+1,-1,k));
                    caches.put(cache, a);
                    return a;
                case 1:
                    a = Math.max(values[r][1]+maxValue(r+1,1,k-1), values[r][1]+values[r][0]+maxValue(r+1,-1,k));
                    caches.put(cache, a);
                    return a;
                case -1:
                    a = Math.max(values[r][0]+maxValue(r+1,0,k-1),values[r][1]+maxValue(r+1,1,k-1));
                    a = Math.max(a,values[r][0]+values[r][1]+maxValue(r+1,-1,k));
                    caches.put(cache, a);
                    return a;
                default:
                    return 0;
            }
        }
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
