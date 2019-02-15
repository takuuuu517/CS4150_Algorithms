package com.company;

import java.util.*;


public class Main {

    private static HashMap<String, ArrayList<String>> friendslist = new HashMap<>(); // main list
    private static String[] startingStudents;
    private static int starternum;
    
    
    private static HashSet<String> visitedStudents = new HashSet<>();

    private static ArrayList<String> pre_time = new ArrayList<>();
    private static ArrayList<String> post_time = new ArrayList<>();

    private static int studentnum;
    private static int connectionNum;


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        studentnum = scan.nextInt();
        for(int i = 0; i < studentnum; i++) // get all students.
        {
            String student = scan.next();
            friendslist.put(student, new ArrayList<>());
        }

        connectionNum = scan.nextInt();
        for(int i = 0; i < connectionNum; i++) // get all students connection.
        {
            String s1 = scan.next();
            String s2 = scan.next();
            friendslist.get(s1).add(s2);
        }


        starternum = scan.nextInt();
        startingStudents = new String[starternum];

        topological_sort(friendslist,visitedStudents);


        for(int i = 0; i < starternum; i++) // get all of the students who start the rumor.
            startingStudents[i] = scan.next();

        System.out.print("");
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
}
