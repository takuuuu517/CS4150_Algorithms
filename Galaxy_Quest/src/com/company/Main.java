package com.company;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static int d;
    static int starnum;
    static int[] yes = {-1,-1};
    static int[] no = {-2,-2};

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        d = scan.nextInt();
        starnum = scan.nextInt();

        int[][] stars = new int[starnum][2];

        for (int i = 0; i < starnum; i++)
        {
            int x = scan.nextInt();
            int y = scan.nextInt();
            int[] star = {x,y};
            stars[i] = star;
        }

        int[][] mm = is_majority(stars,0,stars.length-1);

        if(Arrays.equals(mm[0], no))
            System.out.print("NO");
        else
        {
            int[][] fake = {{-3,-3}};
            int c = count(mm[1],mm,fake);
            System.out.print(c);
        }
    }


    /**
     * first element indicates whether there is an majority. [-1,-1] for "yes" and [-2,-2] for "no"
     * if yes, the second element is the majority galaxity.
     */
    static int[][] is_majority(int[][] stars, int lo, int hi)
    {
        if(lo >= hi)
        {
            int[][] majority = {{-1,-1},stars[lo]};
            return majority;
        }

        int middle = lo+(hi-lo)/2;

        int[][] left_majo = is_majority(stars, lo, middle);
        int[][] right_majo = is_majority(stars, middle+1, hi);



        int result_length = left_majo.length + right_majo.length -1 ;

        int left_ma[] = left_majo[1];
        int right_ma[] = right_majo[1];

        // there are three cases, {yes, yes}, {no,no}, or {yes, no}

        //{ yes, yes}
        if(Arrays.equals(left_majo[0], yes) && Arrays.equals(right_majo[0], yes))
        {
            // yes or no
            if(!isSameGalaxy(left_ma,right_ma))
            {
                int left_count = count(left_ma,left_majo, right_majo);
                int right_count = count(right_ma,right_majo,left_majo);

                if(left_count > (starnum/2))
                    return merge(left_majo,right_majo,result_length,true,left_ma);
                else if (right_count > (starnum/2))
                    return merge(left_majo,right_majo,result_length,true,right_ma);
                else
                    return merge(left_majo,right_majo,result_length,false,left_ma);
            }

            // yes
            else
                return merge(left_majo,right_majo,result_length,true,left_ma);
        }
        else if (Arrays.equals(left_majo[0], no) && Arrays.equals(right_majo[0], no)) // no + no = no
        {
            int[] fake_majority = {-3,-3};
            return merge(left_majo,right_majo,result_length, false,fake_majority );
        }
        else // yes no
        {
            int[] majority;
            int yes_count;
            if(Arrays.equals(left_majo[0], yes))
            {
                yes_count = count(left_ma,left_majo,right_majo);
                majority = left_ma;
            }
            else
            {
                yes_count = count(right_ma,right_majo,left_majo);
                majority = right_ma;

            }

            if(yes_count > ((left_majo.length+right_majo.length-2)/2))
                return merge(left_majo,right_majo,result_length,true,majority);
            else
                return merge(left_majo,right_majo,result_length,false,left_ma);
        }

    }

    static int count(int[] major, int[][] arr1, int[][] arr2 )
    {
        int count=0;

        for(int i = 1; i < arr1.length; i++) {
            if (isSameGalaxy(major, arr1[i]))
                count++;
        }
        for(int i = 1; i < arr2.length; i++) {
            if (isSameGalaxy(major, arr2[i]))
                count++;
        }
        return count;
    }

    static boolean isSameGalaxy(int[] left, int[] right)
    {
        return  Math.pow(left[0]-right[0],2) + Math.pow(left[1]-right[1],2) <= Math.pow(d,2);
    }

    static int[][] merge(int[][] left, int[][] right, int length, boolean is_yes, int majority[]) {
        int[][] result = new int[length][2];
        int n = 2;
        int conti = 1;

        if (is_yes)
        {
            result[0] = yes;
            result[1] = majority;
            for(int i = 1; i < left.length; i++)
            {
                if(Arrays.equals(majority,left[i]))
                {
                    conti--;
                    continue;
                }
                result[n] = left[i];
                n++;
            }
            n = left.length + conti;
            for(int i = 1; i < right.length; i++)
            {
                if(Arrays.equals(majority,right[i]))
                    continue;
                result[n] = right[i];
                n++;
            }
            return result;
        }
        else
            result[0] = no;

        for(int i = 1; i < left.length; i++)
            result[i] = left[i];
        for(int i = 1; i < right.length; i++)
            result[i+left.length-1] = right[i];

        return result;
    }
}
