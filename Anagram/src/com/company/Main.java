package com.company;


import java.util.Arrays;
import java.util.Hashtable;
import java.util.Random;
import java.util.Scanner;


public class Main {


    public static void main(String[] args) {
//        hw1();


//        String[] arr = {"tape","rate","seat","pate","east","pest"};
//
//        System.out.println(experience(6,4,arr));

//        random_strings(10,5);
        timing();

    }

    private static void hw1()
    {
        Scanner scan = new Scanner(System.in);
        int n_words = scan.nextInt();

        // store every words in an array
        String[] words = new String[n_words];
        for (int i = 0; i < n_words; i++)
        {
            String w = scan.next();
            char[] ch = w.toCharArray();
            Arrays.sort(ch);
            w = new String(ch);
            words[i]=w;
        }

        System.out.print(n_words-ana_count(words));

    }


    /**
     * return the
     * @param arr
     * @return
     */
    private static int ana_count(String[] arr)
    {
        int count = 0;
        int prev = 0;
        for (int i = 0; i< arr.length; i++)
        {


            prev = count ;
            if(arr[i] != null)
            {
                String w = arr[i];
                arr[i] = null;

                for (int j = i; j< arr.length; j++)
                {
                    if(arr[j] != null)
                    {
                        if(w.equals(arr[j]))
                        {
                            count++;
                            arr[j]=null;
                        }
                    }
                }
            }

            if (prev != count )
            {
                count++;
            }

        }
        return count;
    }


    /**
     * time this method.
     * @param n
     * @param k
     * @param word_list
     * @return
     */
    private static int experience(int n, int k, String[] word_list)
    {
        String[] words = new String[n];
        for (int i = 0; i < n; i++)
        {
            String w = word_list[i];
            char[] ch = w.toCharArray();
            Arrays.sort(ch);
            w = new String(ch);
            words[i]=w;
        }

        return n - ana_count(words);
    }

    /**
     * create a array containing n words of length of k.
     * @param n
     * @param k
     * @return
     */
    private static String[] random_strings(int n, int k)
    {

        String[] result = new String[n];
        Random r = new Random();

        int a = 97; // a in ascii
        int z = 122; // z in ascii

        int letter;

        for(int i = 0; i < n; i++)
        {
            char[] word = new char[k];
            for(int j = 0; j < k; j++)
            {
                letter = r.nextInt(z-a+1) + a;

                word[j] = (char)letter;
            }
            result[i] = new String(word);
        }
//        // for testing
//        for(int i = 0; i < n; i++)
//        {
//            System.out.println(result[i]);
//        }


        return result;
    }



    private static void timing()
    {
        long DURATION = 1000; //1000 milli sec = 1 sec

//        int n = 2000;
        int k = 5;


        for(int n = 10; k < 16385; n*=2)
        {
            String[] list = random_strings(n,k);

            double elapsed = 0;
            long repetition = 1;
            do{
                repetition *=2;
                long startTime = System.nanoTime();
                for(long i = 0 ; i < repetition; i++)
                {
                    experience(n,k,list);
                }
                long stopTime = System.nanoTime();
                elapsed = msecs(stopTime-startTime);
            }while(elapsed<DURATION);
            double totalAverage = elapsed/repetition;

//        System.out .print("hello1");


            elapsed = 0;
            repetition = 1;
            do{

                repetition *=2;
                long startTime = System.nanoTime();
                for(long i = 0 ; i < repetition; i++)
                {
//                experience(n,k,list);
                }
                long stopTime = System.nanoTime();
                elapsed = msecs(stopTime-startTime);
//            System.out.print(msecs(stopTime-startTime));
            }while(elapsed<DURATION);
            double overheadAverage = elapsed/repetition;


//        System.out.print("hello2");


            System.out.println(n+"\t"  + (totalAverage - overheadAverage)+"\t"  + overheadAverage);

        }

    }

    /**
     * convert nano sec to milli sec
     * @param time
     * @return
     */
    private static long msecs(long time)
    {
        return time/1000000;
    }



}
