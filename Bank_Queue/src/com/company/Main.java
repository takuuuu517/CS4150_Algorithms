package com.company;

import java.io.IOException;
import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;

public class Main {


    static int people;
    static int time;
    static int[] amount;

    static int money;
    static int wait;


    public static void main(String[] args) {

        FastScanner scan = new FastScanner();

        people = scan.nextInt();
        time= scan.nextInt();

        amount = new int[time];
        PriorityQueue<Integer> pq = new PriorityQueue<>();


        for(int i = 0; i < people; i++) {
            money = scan.nextInt();
            wait = scan.nextInt();

//            if(amount[wait] == 0){
//                amount[wait] = money;
//                pq.add(money);
//                while(pq.size() <= wait)
//                    pq.add(0);
//            }

            while (pq.size() <= wait)
                pq.add(0);

            while (pq.size() > wait + 1) {
                if (pq.peek() == 0)
                    pq.poll();
                else
                    break;
            }


            if (amount[wait] == 0){
                pq.add(money);
                amount[wait] = money;
            }
            else if (pq.peek() < money) {
                pq.add(money);
                for(int f = 0; f < wait; f++){
                    if(amount[f] == 0){
                        amount[f] = money;
                        break;
                    }
                }
                pq.poll();
            }

//            else if(amount[wait] > money){
//                if(pq.peek() < money){
//                    pq.poll();
//                    pq.add(money);
//                }
//            }
//            else if(amount[wait] < money){
//                int t = amount[wait];
//                amount[wait] = money;
//                pq.add(money);
//                if(pq.peek() < t)
//                    pq.poll();
//            }



        }

        int sum = 0;

        while (pq.size() != 0){
            sum += pq.poll();
        }
        System.out.println(sum);
    }



//    static void check(){
//        for()
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
