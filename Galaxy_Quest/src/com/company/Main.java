package com.company;
import java.awt.image.AreaAveragingScaleFilter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static int d;
    static int starnum;
    static int[] yes = {-1,-1};
    static int[] no = {-2,-2};

    public static void main(String[] args) {


//        int a[] = {1,1};
//        int b[] = {1,1};
//        boolean c = (Arrays.equals(a,b));
//        System.out.print(c);

        Scanner scan = new Scanner(System.in);
        d = scan.nextInt();
        starnum = scan.nextInt();

        int[][] stars = new int[starnum][2];

        // store every words in an array
        for (int i = 0; i < starnum; i++)
        {
            int x = scan.nextInt();
            int y = scan.nextInt();
            int[] star = {x,y};
            stars[i] = star;
        }

        for (int i = 0; i < starnum; i++)
        {
            for(int j = 0; j < 2; j++){}
//                System.out.print(stars[i][j]+" ");

//            System.out.println();
        }

//        System.out.println();
//        System.out.println();


        int[][] mm = is_majority(stars,0,stars.length-1);
        for (int i = 0; i < mm.length; i++) {
            for (int j = 0; j < mm[0].length; j++){}
//                System.out.print(mm[i][j] +" ");

//            System.out.println();
        }


        if(Arrays.equals(mm[0], no))
            System.out.print("NO");
        else
        {
            int[][] fake = {{-3,-3}};
            int c = count(mm[1],mm,fake);
            System.out.print(c);
        }


//        starnum =10;
//        int[][] g = {{1,1}, {1,2}, {1,3},{1,4}, {1,5}, {1,6}, {1,7}, {1,115}, {1,116}, {1,117}  };
//        int[][] mmm = is_majority(g,0,g.length-1);
//
//        System.out.print("result \n\n");
//
//        for (int i = 0; i < mmm.length; i++) {
//            for (int j = 0; j < mmm[0].length; j++)
//                System.out.print(mmm[i][j] +" ");
//
//            System.out.println();
//        }
//        int[][] fake = {{-3,-3}};
//        int c = count(mmm[1],mmm,fake);
//        System.out.print(c);




    }


    /**
     * first element indicates whether there is an majority. [-1,-1] for "yes" and [-2,-2] for "no"
     * if yes, the second element is the majority galaxity.
     */
    static int[][] is_majority(int[][] stars, int lo, int hi)
    {
//        if(stars.length == 1) // base case
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
//                result[i+1] = left[i];
                result[n] = left[i];
                n++;
            }
            n=left.length + conti;
            for(int i = 1; i < right.length; i++)
            {
                if(Arrays.equals(majority,right[i]))
                {
//                    n--;
                    continue;
                }
//                result[i+left.length-1+n] = right[i];
                result[n] = right[i];
                n++;
            }

            return result;

        }
        else
            result[0] = no;

        for(int i = 1; i < left.length; i++)
        {
            result[i] = left[i];
        }
        for(int i = 1; i < right.length; i++)
        {
            result[i+left.length-1] = right[i];
        }


        return result;
    }
}
