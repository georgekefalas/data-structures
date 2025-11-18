//Georgios Kefalas, A.M: 5252
//Dimitrios Papadopoulos, A.M: 5321
//Group 3, ID 3

import java.io.*;
import java.util.Random;

// minimum index priority queue implemented with a binary heap
public class IndexMinPQ<Key extends Comparable<Key>>
{
    private int N;           // number of items on priority queue
    private int   pq[];      // binary heap of items with respect to their keys
    private int   index[];   // index[j] = position of item j in pq : pq[index[j]]=index[pq[j]]=j
    private Key[] keys;      // key[j]   = priority of item j

    public IndexMinPQ(int maxN)
    {
        N=0;
        keys   = (Key []) new Comparable[maxN+1];
        pq     = new int[maxN+1];
        index  = new int[maxN+1];
        for (int i = 0; i <= maxN; i++) index[i]=-1;
    }

    // check if priority queue is empty
    public boolean isEmpty() {
        return N == 0;
    }

    // check if priority queue contains item j
    public boolean contains(int j) {
        return index[j] != -1;
    }

    // compare keys of items pq[i] and pq[j]
    private boolean less(int i, int j) {
        return keys[pq[i]].compareTo(keys[pq[j]]) < 0;
    }

    // exchange the position of items pq[i] and pq[j] in array pq[]
    private void exch(int i, int j) {
        int t=pq[i]; pq[i]=pq[j]; pq[j]=t;
        index[pq[i]]=i;
        index[pq[j]]=j;
    }

    // return the key of item j
    public Key getKey(int j) {
        return keys[j];
    }

    //fixp method
    private void fixUp(int k)
    {
        while (k>1 && less(k, k/2)) {
            exch(k,k/2);
            k=k/2;
        }
    }

    //fixdown method
    private void fixDown(int k)
    {   
        int j;
        while (2*k <= N)
        { 
            j=2*k;
            if (j<N && less(j+1,j)) j++;
            if (less(k,j)) break;
            exch(k,j); k=j;
        }
    }

    // insert item j with a key
    public void insert(int j, Key key) {
        pq[++N] = j;
        index[j] = N;
        keys[j] = key;
        fixUp(N);
    }

    // delete and return item with minimum key
    public int delMin() 
    {
        int minKeyItem = pq[1];
        exch(1,N);
        N--;
        fixDown(1);
        index[pq[N+1]] = -1;
        
        return minKeyItem;
    }
    
    // return item with minimum key
    public int minItem()
    {
        return pq[1];
    }

    // change the key of item j
    public void change(int j, Key key) 
    {
        Key oldKey = keys[j];
        keys[j] = key;
        int k = index[j];
        if ( key.compareTo(oldKey) < 0 )
            fixUp(k);
        else
            fixDown(k);
    }

    // print pq, key and index arrays
    public void printPQ()
    {
        for (int j=1; j<=N; j++)
            System.out.println("pq["+j+"]= " + pq[j] + ", key= " + keys[pq[j]]);

        for (int j=0; j<N; j++)
            System.out.println("index["+j+"]= " + index[j]);
    }

    public static void main(String[] args) {
        System.out.println("Test Index Min Priority Queue");

        int N = Integer.parseInt(args[0]);
        System.out.println("Number of items to be inserted = " + N);

        long startTime = System.currentTimeMillis();
        IndexMinPQ<Integer> PQ = new IndexMinPQ<Integer>(N);
        Random rand = new Random(0);

        for (int i=0; i<N; i++) {
            int key = rand.nextInt(N*N); // assign random keys
            System.out.println("insert item " + i + " key " + key);
            PQ.insert(i,key);
        }
        PQ.printPQ();
        while ( !PQ.isEmpty() ) {
            int key = PQ.getKey(PQ.minItem());
            int k = PQ.delMin();
            System.out.println("delMin item " + k + " key " + key);
        }
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("total time = " + totalTime);
    }
}
