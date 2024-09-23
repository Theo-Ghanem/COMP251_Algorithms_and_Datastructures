import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.style.markers.SeriesMarkers;

import java.io.*;
import java.util.*;

/**
 * This is a class to test the lookup time in a hash Table implement with open Addressing
 * This is mostly code I wrote for my first assignment in Comp 251
 * @author Theo Ghanem
 */
public class Open_Addressing {
    public int m; // number of SLOTS AVAILABLE
    public int A; // the default random number
    int w;
    int r;
    public int[] Table;

    protected Open_Addressing(int w, int seed, int A) {

        this.w = w;
        this.r = (int) (w-1)/2 +1;
        this.m = power2(r);
        if (A==-1){
            this.A = generateRandom((int) power2(w-1), (int) power2(w),seed);
        }
        else{
            this.A = A;
        }
        this.Table = new int[m];
        for (int i =0; i<m; i++) {
            Table[i] = -1;
        }

    }

    /** Calculate 2^w*/
    public static int power2(int w) {
        return (int) Math.pow(2, w);
    }
    public static int generateRandom(int min, int max, int seed) {
        Random generator = new Random();
        if(seed>=0){
            generator.setSeed(seed);
        }
        int i = generator.nextInt(max-min-1);
        return i+min+1;
    }
    /**Implements the hash function g(k)*/
    public int probe(int key, int i) {
        int hashK = (A*key)%(power2(w)) >> (w-r) ;
        int hashValueG = (hashK + i) % power2(r);
        return hashValueG; //returns a value in [0,m]
    }


    /**Inserts key k into hash table. Returns the number of collisions encountered*/
    public void insertKey(int key){
        int attempt = 0;                   //initialize attempt to 0
        while (attempt<m){                 //don't attempt more than the number of buckets
            int j = probe(key, attempt);   //get the hashvalue
             if (Table[j] == key) return;  //if the slot contains the key return the number of attempts
            if (Table[j] < 0){             //if the slot has a -1 then it is empty, if it has a -2 then it's previous value was deleted
                Table[j] = key;            //so we can store the key in that slot
                return;                   //return the number of attempts it took before storing the key
            }
            else ++attempt;               //if we haven't found an empty slot attempt again
        }

        return;                            //returns number of attempts it took before giving up
    }

    /**Looks up a key k in hash table. Returns the number of collisions encountered*/
    public int lookupKey(int key){
        int attempt = 0;                           //initialize attempt to 0
        while (attempt<m){                         //don't attempt more than the number of buckets
            int j = probe(key, attempt);           //get the hashvalue
             if (Table[j] == key) return attempt;  //if the slot contains the key return the number of attempts
            if (Table[j] < 0){                     //if the slot has a -1 then it is empty, if it has a -2 then it's previous value was deleted
                return attempt;                    //return the number of attempts it took before storing the key
            }
            else ++attempt;                        //if we haven't found an empty slot attempt again
        }
        return attempt;                            //returns number of attempts it took before giving up
    }


    /**
     * Method that creates the hash table with open addressing and computes the lookup time
     * @param numberOfKeys number of keys we are inserting in the hash table
     * @return the run time
     * @author Theo Ghanem
     */
    public static double runTimeHashTable(int numberOfKeys){

        Random r = new Random();
        int lowVal = 10;
        int highVal = 100;
        int resultRand = r.nextInt(highVal-lowVal) + lowVal;
        //I chose this size of the hash table to get an optimal load factor of 75%, which is
        //a good balance between not too many collisions and not too much empty space
        int sizeOfTable = (numberOfKeys*100)/75;
        Open_Addressing hashTable =  new Open_Addressing(10, 0, -1);
        for(int i=0; i<numberOfKeys;i++)
            hashTable.insertKey(i);
        double start = System.nanoTime();
        int attemptsToLookUpKey = hashTable.lookupKey(resultRand);
        double end = System.nanoTime();

        // execution time in microseconds
        double duration = (end - start) / 1000;
        return duration;
    }




    /**
     * Main method that runs the hash table on a series of samples and outputs a chart of the runtime
     * @author Readapted from provided example
     */
    public static void main(String[] args) {

        // number of sample executions
        int samples = 1000;
        double[] ns = new double[samples];
        double[] execution_times = new double[samples];
        int n = 7;
        for (int i=8; i<samples; i++) {
            // run bellman ford on a random graph with n vertices and 2n edges
            execution_times[i] = runTimeHashTable(i);
            ns[i] = n;
            n += 1;
        }

        // create chart
        XYChart chart = QuickChart.getChart("Execution Time of searching in Hash Table with open-addressing",
                "Size of Hash Table & number of keys", "Execution Time (us)",
                "Search Hash Table Runtime", ns, execution_times);
        double[] n2s = new double[samples];
        // add reference quadratic
        for (int i=0; i<samples; i++) {
            n2s[i] = (1);
        }
        chart.addSeries("1", ns, n2s).setMarker(SeriesMarkers.NONE);
        // display chart
        new SwingWrapper<>(chart).displayChart();

        // save chart
        try {
            BitmapEncoder.saveBitmapWithDPI(chart, "./Run_Time_Chart", BitmapEncoder.BitmapFormat.PNG, 300);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
