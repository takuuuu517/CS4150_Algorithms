package com.company;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class Main {
    static int n;
    static int[][] cache;
    static int[][] decisions;
    static int[] distances;


    public static void main(String[] args) {
        FastScanner scan = new FastScanner();

        n = scan.nextInt();
        boolean b = true;

        int num;
        int max;


        for(int i = 0; i< n; i++){
            num = scan.nextInt();
            max = 0;
            distances = new int[num];
            for(int j = 0; j < num; j++){
                int d = scan.nextInt();
                distances[j] = d;
                max += d;
            }
            if(max % 2 != 0) {// easy check
                System.out.println("IMPOSSIBLE");
                b = !b;
                continue;
            }

            cache = new int[num+1][max+1];
            decisions = new int[num+1][max+1];

            // initialize
            for(int j = 0; j< num+1; j++){
                for(int k =0; k < max+1; k++){
                    cache[j][k] = Integer.MAX_VALUE;
                }
            }
            cache[0][0] = 0; // starting point
            determineAction2();

        }
    }

    private static void determineAction2(){
        int n = cache.length-1;
        int max = cache[0].length;

        for(int i = 0; i< n; i++){
            for(int j = 0; j < max; j++){
                int heightSoFar = cache[i][j]; // usually Integer.MAX_VALUE
                if(heightSoFar == Integer.MAX_VALUE)
                    continue;

                // there are two cases
                int up = j + distances[i];
                int down = j - distances[i];

                if(cache[i+1][up] > Math.max(up, heightSoFar)){
                    cache[i+1][up] = Math.max(up, heightSoFar);
                    decisions[i+1][up] = 1; // 1 = up , 2 == down
                }
                if(down >=0 && cache[i+1][down] > Math.max(down,heightSoFar)){ // down is at least 0
                    cache[i+1][down] = Math.max(down,heightSoFar);
                    decisions[i+1][down] = 2;
                }
            }
        }

        if(cache[distances.length][0] == Integer.MAX_VALUE)
            System.out.println("IMPOSSIBLE");
        else { // there is a solution
            StringBuilder sb = new StringBuilder();
            int a = distances.length;
            int dis =0;
            while(a > 0){
                if(decisions[a][dis] == 1) // up -> minus distance
                {
                    sb.insert(0,"U");
                    dis -= distances[--a];
                }else {
                    sb.insert(0,"D");
                    dis += distances[--a];
                }
            }
            System.out.println(sb.toString());
        }

    }


//    private static int determineAction(int highestSoFar, int heightSoFar, int[] distances, String routes1, String route2, int point) {
//
//        StringBuilder sb = new StringBuilder(routes1);
//        StringBuilder sb2 = new StringBuilder(routes1);
//
//        if(routes1.length() == 0){ // first time
//            sb.append("U");
//            String s = sb.toString();
//            if(cache.containsKey(s))
//                return cache.get(s);
//            else
//                cache.put(s, distances[point]);
//            return determineAction( distances[point],  distances[point], distances, s,s, point+1);
//        }
//        else if(routes1.length() == distances.length){ // end
//            if(heightSoFar == 0){
//                result.put(highestSoFar, sb.toString());
//                return highestSoFar;
//            }
//            else{
//                return Integer.MAX_VALUE;
//            }
//        }
//        else{
//            sb.append("U");
//            int u;
//            String s = sb.toString();
//            if(heightSoFar < heightSoFar+distances[point]){
//                int newheightSoFar = heightSoFar+distances[point];
//                if(cache.containsKey(s))
//                    u = cache.get(s);
//                else {
//                    cache.put(s, highestSoFar + distances[point]);
//                    u = determineAction(newheightSoFar, newheightSoFar, distances, s,s, point + 1);
//                }
//            }else{
//                if(cache.containsKey(s))
//                    u = cache.get(s);
//                else{
//                    cache.put(s,highestSoFar+distances[point]);
//                    u = determineAction(highestSoFar,heightSoFar+highestSoFar+distances[point],distances,s,s,point+1);
//                }
//            }
//
//            sb2.append("D");
//            String s2 = sb2.toString();
//            int d;
//            if(heightSoFar - distances[point] < 0){
//                d = Integer.MAX_VALUE;
////                if(cache.containsKey(s2))
////                    d = cache.get(s2);
////                else{
////                    cache.put(s2, Integer.MAX_VALUE); // MAX_VALUE indicates impossible
////                    d = Integer.MAX_VALUE;
////                }
//            }else{
//                if(cache.containsKey(s2))
//                    d = cache.get(s2);
//                else{
//                    cache.put(s2, highestSoFar-distances[point]);
//                    d = determineAction(highestSoFar,heightSoFar-distances[point],distances,s2,s2,point+1);
//                }
//            }
//            return Math.min(u,d);
//        }
//    }
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
