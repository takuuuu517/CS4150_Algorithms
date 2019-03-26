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
    static amount_wait[] amount_wait;


    public static void main(String[] args) {
        FastScanner scan = new FastScanner();

        people = scan.nextInt();
        time= scan.nextInt();

        amount_wait = new amount_wait[people];

        PriorityQueue<amount_wait> pq = new PriorityQueue<>();


        for (int i =0; i < people; i++){
            money = scan.nextInt();
            wait = scan.nextInt();

            amount_wait a = new amount_wait(money,wait);
            pq.add(a);
        }

        int max = pq.peek().wait+1;
        int moneyqueue[] = new int[max];


//        int s = -1;
//        int sum = 0;
        for(int i =0; i< people; i++){
            amount_wait aw = pq.poll();
//            if(s != aw.wait){
//                if(s != -1)
//                    sum += moneyqueue[s];
//                s = aw.wait;
//            }
            int m = aw.amount;
            if(moneyqueue[aw.wait] == 0){
                moneyqueue[aw.wait] = m;
            }
            else {
                for(int j = aw.wait; j >= 0; j--){
                    if(moneyqueue[j] < m){
                        int temp = moneyqueue[j];
                        moneyqueue[j] = m;
                        m = temp;
                    }
                }
            }

        }

        int sum = 0 ;
        for(int i = 0; i<max; i++){
            sum += moneyqueue[i];
        }


        System.out.println(sum);
//        System.out.println(s);

    }
}


class amount_wait implements  Comparable<amount_wait>{
    public int amount;
    public int wait;

    amount_wait(int amount, int wait)
    {
        this.amount = amount;
        this.wait = wait;
    }

    @Override
    public int compareTo(amount_wait o) {
        int a = Integer.compare(o.wait, this.wait);
        if(a == 0)
            return Integer.compare(o.amount, this.amount);
        return a;
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
