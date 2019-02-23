package com.company;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

public class Main {
    private static ArrayList<HashMap<Integer,Double>> connection;
    private static HashMap<Integer, Double> dist = new HashMap<>();
    private static HashMap<Integer, Integer> prev = new HashMap<>();
    private static NumberFormat formatter = new DecimalFormat("#0.0000");

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt();
        int m = scan.nextInt();

        int x, y;
        double f;

        while(!(n == 0 && m == 0))
        {
            connection = new ArrayList();
            for(int i = 0; i < n; i++)
            {
                connection.add(new HashMap<>());
            }

            for(int i = 0 ; i < m; i++)
            {
                x = scan.nextInt();
                y = scan.nextInt();
                f = scan.nextDouble();

                connection.get(x).put(y,f);
                connection.get(y).put(x,f);
            }

            dijkstra(connection,0, n);

            n = scan.nextInt();
            m = scan.nextInt();
        }
        System.out.println();
    }

    private static void dijkstra (ArrayList<HashMap<Integer,Double>> g, int start, int n)
    {

        for(int i = 0; i < g.size(); i++)
        {
            dist.put(i, -1.0);
            prev.put(i, -1);
        }
        dist.put(0,1.0);

        PriorityQueue<verweight> pq = new PriorityQueue(g.size(), Collections.reverseOrder());
        pq.add(new verweight(0,1.0));

        while(!pq.isEmpty())
        {
            verweight v = pq.poll();
            if(v.weight < dist.get(v.vertex))
                continue;

            for(int v1 : connection.get(v.vertex).keySet())
            {
                double dis =   connection.get(v.vertex).get(v1) * v.weight;
                if (dist.get(v1) < dis)
                {
                    dist.put(v1, dis );
                    prev.put(v1,v.vertex);
                    pq.add(new verweight(v1, dist.get(v1)));
                }
            }
        }

        System.out.println(formatter.format(dist.get(n-1)));
    }
}

class verweight implements Comparable<verweight>
{
    int vertex;
    double weight;
    public verweight(int vertex, double weight)
    {
        this.vertex = vertex;
        this.weight = weight;
    }

    @Override
    public int compareTo(verweight o) {
        int a = Double.compare(this.weight,o.weight);
        if(a == 0)
            return Integer.compare(this.vertex,o.vertex);
        return a;
    }
}