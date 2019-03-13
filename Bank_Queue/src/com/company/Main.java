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


//        for (int i =0; i < people; i++){
//            amount_wait a = pq.poll();
//            StringBuilder sb = new StringBuilder();
//            sb.append(a.amount).append(" ").append(a.wait);
//            System.out.println(sb.toString());
//        }

        int max = pq.peek().wait+1;

        PriorityQueue<Integer> pq2 = new PriorityQueue<>();
        for(int i = 0; i < max; i++)
            pq2.add(0);

        int count = 1;
        for(int i = 0; i < people; i+= count){
            count = 1;
            amount_wait a =pq.poll();
            int amount = a.amount;
            int waittime = a.wait;
            if(pq2.peek() < amount){
                pq2.poll();
                pq2.add(amount);
            }
            while(waittime == pq.peek().wait){
                for(int f = 0; f< waittime; f++ ){
                    a = pq.poll();
                    if(pq2.peek() < a.amount){
                        pq2.poll();
                        pq2.add(a.amount);
                    }
                    count++;
                    if(pq.size() == 0 )
                        break;
                }
            }


        }

        int sum = 0;
        for(int i = 0; i < max; i++){
            sum += pq2.poll();
        }
        System.out.println(sum);


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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.amount).append(" ").append(this.wait);

        return sb.toString();
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
