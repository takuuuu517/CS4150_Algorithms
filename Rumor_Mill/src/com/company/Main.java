package com.company;

import java.util.*;


public class Main {

    private static HashMap<String, ArrayList<String>> friendslist = new HashMap<>(); // main list
    private static String[] startingStudents;
    private static int starternum;

//    private static HashSet<String> visitedStudents = new HashSet<>();
    private static HashMap<String, Integer> dist = new HashMap<>();
    private static HashMap<String, String> prev = new HashMap<>();

    private static int studentnum;
    private static int connectionNum;

    private static final int inf = Integer.MAX_VALUE;

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
            friendslist.get(s2).add(s1);

        }
        starternum = scan.nextInt();
        startingStudents = new String[starternum];

        for(int i = 0; i < starternum; i++) // get all of the students who start the rumor.
        {
            startingStudents[i] = scan.next();
            bfs(friendslist,startingStudents[i]);
            System.out.println(sortMapbyValue(dist));
        }
    }


    static void bfs (HashMap<String, ArrayList<String>> g, String start)
    {
        //initialize
        for(Map.Entry<String, ArrayList<String>> entry : g.entrySet())
        {
            dist.put(entry.getKey(),inf);
            prev.put(entry.getKey(),null);
        }
        dist.put(start,0);

        Queue<String> q = new ArrayDeque<>();
        q.add(start);

        while (!q.isEmpty())
        {
            String u = q.remove();
            for(String v: friendslist.get(u))
                if(dist.get(v) == inf)
                {
                    q.add(v);
                    dist.put(v, add(dist.get(u),1));
                    prev.put(v,u);
                }
        }
    }


    /**
     * simple add method to avoid over flow.
     * @param a
     * @param b
     * @return return a+b normally. if a or b is max int then, return max int.
     */
    private static int add(int a, int b)
    {
        if(a == inf || b == inf )
            return inf;
        return a+b;
    }


    /**
     *
     * @param distmap
     * @return string of of the map in sorted order separated by a space
     */
    private static String sortMapbyValue(HashMap<String, Integer> distmap)
    {
        List<Map.Entry<String, Integer>> list = new ArrayList<>(distmap.entrySet()); // make a list from the map.
        Collections.sort(list, new comparator()); // sort the map using the comparator.
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < list.size(); i++)
        {
            sb.append(list.get(i).getKey());
            sb.append(" ");
        }
        return sb.toString();
    }
}

/**
 * compare the distance from the start first, then if the distances are the same compare the student names.
 */
class comparator implements Comparator<Map.Entry<String, Integer>> {
    public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
        if(o1.getValue().compareTo(o2.getValue()) == 0)
            return o1.getKey().compareTo(o2.getKey());
        return o1.getValue().compareTo(o2.getValue());
    }
}
