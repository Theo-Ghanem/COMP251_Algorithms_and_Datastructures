import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.style.markers.SeriesMarkers;

import java.io.IOException;
import java.util.Hashtable;

/**
 * This is a class to test java's hash Table's searching speed
 * Note that java implements hash tables with chaining rather than open addressing
 *
 * @author Theo Ghanem
 */
public class JAVAhashTableTest {

    /**
     * Method that creates the hash table and computes the lookup time
     * @param numberOfKeys number of keys we are inserting in the hash table
     * @return the run time
     * @author Theo Ghanem
     */
    public static double lookupHashTable(int numberOfKeys){

        //I chose this size of the hash table to get an optimal load factor of 75%, which is a good balance between not too many collisions and not too much empty space
        int sizeOfTable = (numberOfKeys*100)/75;
            Hashtable<Integer, Integer> hashTable = new Hashtable<>(sizeOfTable);
            for(int i=0; i<numberOfKeys;i++)
                hashTable.put(i, i);
        //System.out.println(hashTable);
        double start = System.nanoTime();
        hashTable.get(7);
        double end = System.nanoTime();
        // execution time in microseconds
        double duration = (end - start) / 1000;
        return duration;
        }


    // runs the hash table on a series of samples and outputs a chart of the runtime
    public static void main(String[] args) {

        // number of sample executions
        int samples = 1000;
        double[] ns = new double[samples];
        double[] execution_times = new double[samples];
        int n = 7;
        for (int i=8; i<samples; i++) {
            // run bellman ford on a random graph with n vertices and 2n edges
            execution_times[i] = lookupHashTable(i);
            ns[i] = n;
            n += 1;
        }

        // create chart
        XYChart chart = QuickChart.getChart("Execution Time of searching in java's Hash Table ", "Size of Hash Table & number of keys", "Execution Time (us)", "Search Hash Table Runtime", ns, execution_times);
        double[] n2s = new double[samples];
        // add reference quadratic
        for (int i=0; i<samples; i++) {
            n2s[i] = (1);
        }
        chart.addSeries("1", ns, n2s).setMarker(SeriesMarkers.NONE);;
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
