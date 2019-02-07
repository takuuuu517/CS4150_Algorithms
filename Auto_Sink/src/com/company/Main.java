package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Map;


public class Main {
    private static HashMap<String, Integer> city_price = new HashMap<>();
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

            System.out.println(findCost(pathes[i][0], pathes[i][1]));
        }

        System.out.print("");
    }

    static String findCost(String cityFrom, String cityTo)
    {
        if(is_no(cityFrom,cityTo))
            return "NO";

        // there is way to go... find the min cost.
        // count the cost while iterate through the list in reverse topological order
        // TODO




        return "0";

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
        boolean result = true;
        if(cityFrom.equals(cityTo))
            return false;
        for(String city : cityAccesslist.get(cityFrom)){
            if(cityTo.equals(city))
                return false;
//            result = is_no(city, cityTo);
            return is_no(city, cityTo);
//            if(result == false)
//                break;
        }
        return result;
    }

}
