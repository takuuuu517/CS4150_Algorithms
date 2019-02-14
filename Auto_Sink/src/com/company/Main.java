package com.company;

import java.util.*;


public class Main {
    private static HashMap<String, Integer> city_price = new HashMap<>();
    private static HashMap<String, Integer> city_price_total = new HashMap<>();

    private static HashMap<String, ArrayList<String>> cityAccesslist = new HashMap<>(); // main list
    private static HashSet<String> visitedCity = new HashSet<>();
    private static String[][] pathes;

    private static ArrayList<String> pre_time = new ArrayList<>();
    private static ArrayList<String> post_time = new ArrayList<>();

    private static int citynum;
    private static int connectionNum;
    private static int pathnum;


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        citynum = scan.nextInt();
        for(int i = 0; i < citynum; i++) // get all cities
        {
            String city = scan.next();
            int cost = scan.nextInt();
            city_price.put(city,cost);
            cityAccesslist.put(city, new ArrayList<>());
        }

        connectionNum = scan.nextInt();
        for(int i = 0; i < connectionNum; i++) // get all cities
        {
            String parent = scan.next();
            String child = scan.next();
            cityAccesslist.get(parent).add(child);
        }

        pathnum = scan.nextInt();
        pathes = new String[pathnum][2];
        topological_sort(cityAccesslist,visitedCity);


        for(int i = 0; i < pathnum; i++) // get all cities
        {
            pathes[i][0] = scan.next();
            pathes[i][1] = scan.next();
            String a = findCost(pathes[i][0], pathes[i][1]);
            if(a.equals("-1"))
                System.out.println("NO");
            else
                System.out.println(a);
        }

//        System.out.print("");
    }

    static String findCost(String cityFrom, String cityTo)
    {
        city_price_total.clear();



        if(is_no(cityFrom,cityTo))
            return "NO";
        else if(cityFrom.equals(cityTo))
            return "0";

        // there is way to go... find the min cost.
        // count the cost while iterate through the list in reverse topological order
        // TODO
        int i = post_time.indexOf(cityTo);
        int dest = post_time.indexOf(cityFrom);
        int result = 0;
        while(i >= dest)
        {
            String city = post_time.get(i);
            result = citycost(city, cityTo);
            i--;
        }

        return Integer.toString(result);
    }


    static int citycost(String city, String cityTo)
    {
        int[] totalcosts = new int[cityAccesslist.get(city).size()];
        boolean not_connected_to_dest = true;
        int i = 0;
        for(String connected_city: cityAccesslist.get(city))
        {
            if(city_price_total.containsKey(connected_city))
            {
                if(city_price_total.get(connected_city) == -1)
                    totalcosts[i] = -1;
                else
                {
                    not_connected_to_dest = false;
                    totalcosts[i] = city_price_total.get(connected_city) + city_price.get(connected_city);
                }
            }
            else
                totalcosts[i] = -1;
            i++;
        }

        if(not_connected_to_dest)
        {
            if(city.equals(cityTo))
                city_price_total.put(city, 0);
            else
                city_price_total.put(city, -1);
            return -1;
        }

        int mincost = getPositiveMin(totalcosts);
        city_price_total.put(city, mincost);
        return mincost;
    }


    /**
     *
     * @param totalcosts
     * @return the smallest positive integer in the array
     */
    private static int getPositiveMin(int[] totalcosts) {
        Arrays.sort(totalcosts);
        for (int cost: totalcosts)
            if(cost != -1)
                return cost;
        return 0;
    }



    static void topological_sort(HashMap<String, ArrayList<String>> mlist, HashSet<String> visitedlist)
    {
        visitedlist.clear();
        pre_time.clear();
        post_time.clear();

        for(Map.Entry<String, ArrayList<String>> entry : mlist.entrySet())
        {
            if(!visitedlist.contains(entry.getKey()))
                explore(mlist, visitedlist, entry.getKey());
        }
    }

    /**
     * @param mlist
     * @param visitedlist
     * @param city
     */
    static void explore(HashMap<String, ArrayList<String>> mlist, HashSet<String> visitedlist, String city)
    {
        visitedlist.add(city);
        pre_time.add(city);
        for(int i = 0; i < mlist.get(city).size(); i++)
        {
            if(!visitedlist.contains(mlist.get(city).get(i)))
                explore(mlist,visitedlist, mlist.get(city).get(i));
        }
        post_time.add(0,city);
    }


    /**
     * not sure if were supposed to use recursion...
     * @param cityFrom
     * @param cityTo
     * @return
     */
    static boolean is_no(String cityFrom, String cityTo)
    {
        if(post_time.indexOf(cityFrom) > post_time.indexOf(cityTo))
            return true;
        else
            return false;




//        boolean result = true;
//        if(cityFrom.equals(cityTo))
//            return false;
//        for(String city : cityAccesslist.get(cityFrom)){
//            if(cityTo.equals(city))
//                return false;
//            result = is_no(city, cityTo);
//            if(result == false)
//                break;
//        }
//        return result;

    }

}
