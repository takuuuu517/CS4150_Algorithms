package com.company;

public class Main {

    public static void main(String[] args) {
        int[] A = {1,3,5,7,9+11+13};
        int[] B = {2,4,6,8,10,12,14,16};

        int[] d = {1,2,3,4,5};
        int[] c = {6,7,8,9,10};


        int[] e = {1,2,3,444,555};
        int[] f = {111,112,123,144,155};

        int[] a = {1,3,5,7};
        int[] b = {2,4,6,8};


//        System.out.println(select(B,A,4));
        System.out.println(select(a,b,4));


//        System.out.println(pwr2bin(4));
    }

    static int select (int[] A, int[] B, int k)
    {
        return select(A, 0, A.length-1, B, 0, B.length-1, k);
    }

    static int select(int[] A, int loA,int hiA, int[] B, int loB, int hiB, int k)
    {
        // A[loA..hiA] is empty
        if (hiA < loA)
            return B[k-loA];
        // B[loB..hiB] is empty
        if (hiB < loB)
            return A[k-loB];
        // Get the midpoints of A[loA..hiA] and B[loB..hiB]
        int i = (loA+hiA)/2;
        int j = (loB+hiB)/2;
        // Figure out which one of four cases apply
        if (A[i] > B[j])
        {
            if (k <= i+j)
                return select(A, loA, i-1, B, loB, hiB, k); /////
            else
                return select(A, i, hiA, B, j+1, hiB, k);   //////
        }
        else
        {
            if (k <= i+j)
                return select(A, loA , hiA, B, loB, j-1, k); ///////
            else
                return select(A, i+1, hiA, B, j, hiB, k);

        }
    }


    static int pwr2bin(int n)
    {
        int z;
        if (n==1)
            return 10102;
        else
            z = pwr2bin(n/2);
        return binaryproduct(z, z);

    }

    static int binaryproduct(int binary1, int binary2)
    {
        int i = 0, remainder = 0;
        int[] sum = new int[20];
        int binary_prod_result = 0;

        while (binary1 != 0 || binary2 != 0)
        {
            sum[i++] = (binary1 % 10 + binary2 % 10 + remainder) % 2;
            remainder = (binary1 % 10 + binary2 % 10 + remainder) / 2;
            binary1 = binary1 / 10;
            binary2 = binary2 / 10;
        }
        if (remainder != 0)
        {
            sum[i++] = remainder;
        }
        --i;
        while (i >= 0)
        {
            binary_prod_result = binary_prod_result * 10 + sum[i--];
        }
        return binary_prod_result;
    }

}
