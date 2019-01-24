import java.util.*;


public class Main {

    public static void main(String[] args) {




        Scanner scan = new Scanner(System.in);
        int n_lines = scan.nextInt();
        int k_numbers = scan.nextInt();

        int[][] inputs = new int[n_lines][k_numbers];

        // store every words in an array
        for (int i = 0; i < n_lines; i++)
        {
            for(int j =0; j<k_numbers; j++)
            {
                inputs[i][j] = scan.nextInt();
            }
        }

        // inputs are ready

        String[][] r = new String[n_lines][k_numbers-1];
        for (int i = 0; i < n_lines; i++)
        {
            r[i] = maketable(inputs[i]);
        }

        int count = 0;

        for (int i = 0; i < n_lines; i++)
        {
            if(r[i] == null )
                continue;

            count++;

            int index = i+1;
            while(index < n_lines)
            {
                if(r[index] != null){
                    if(Arrays.equals(r[i],r[index]))
                    {
                        r[index] =null;
                    }
                }

                index++;
            }

//
//            for(int j =0; j<k_numbers-1; j++)
//            {
//                System.out.print(r[i][j]+" ");
//            }
//            System.out.print("\n");
        }
        System.out.print(count);








    }



    private static String[] maketable(int[] arr)
    {
        Hashtable<String, Integer> a  = new Hashtable();

        int root = arr[0];


        for(int i = 1; i < arr.length; i++)
        {
            int self = arr[i];
            StringBuffer key = new StringBuffer();
            if(self < root) // smaller
                key.append("0");
            else
                key.append("1"); // bigger

            while(a.containsKey(key.toString()))
            {
                int comp = a.get(key.toString());
                if(self < comp)
                    key.append("0");
                else
                    key.append("1");
            }

            a.put(key.toString(),arr[i]);
        }
        String[] c = new String[arr.length - 1];
        Arrays.sort(a.keySet().toArray(c));

//        for(int i = 0; i < c.length; i++)
//        {
//            System.out.print(c[i]+" ");
//        }
        return c;




    }


}
