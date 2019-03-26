package com.company;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    public static void main(String[] args) {

//        HashSet a = new HashSet();
//        a.add(1);
//        a.add(2);
//        System.out.println(a.add(1));\



        ThreadLocalRandom r = ThreadLocalRandom.current();

        for (int i= 0 ; i< 100; i++){
//            r.nextInt()
            int randomNum = r.nextInt(2, 101);
            System.out.println(randomNum);


        }


        long a = 11;
        long b = 111;

        long c = a*b;


//        ArrayList<Integer> a = new ArrayList<>();
//        for (int i = 1; i <= 100 ; i++){
//            a.add(i);
//        }
//        Collections.shuffle(a);
//
//        System.out.println(a.contains(3));

//        System.out.println(mod(5,4)); // 1
//        System.out.println(mod(-5,4)); // 3

//        ArrayList a = abc();
//        Collections.sort(a);
//        System.out.println(560-a.size());
    }

    static int mod (int a, int b){
        if (a< 0){
            int s = Math.abs(a) / b + 1;
            int t = a + b * s;

            return t;
        }
        else {
            return a % b;
        }
    }


    static ArrayList<Integer> abc(){
        int a= 3;
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(1);
        while(a < 561){
            arr.add(a);
            a +=3;
        }
        a = 11;
        while(a < 561){
            if(!arr.contains(a))
                arr.add(a);
            a +=11;
        }

        a = 17;
        while(a < 561){
            if(!arr.contains(a))
                arr.add(a);
            a +=17;
        }


        System.out.println(arr.size());
        return arr;

    }





}
