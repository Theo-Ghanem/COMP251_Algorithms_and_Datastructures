import java.sql.Array;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;


public class A2_Q2 {
//    static int passedTests = 0;
//    static int failedTests = 0;
//
//
//    final static String TEST_FOLDER = "Open_Tests/Q2";

//    public static void main(String[] args) {
//        File f = new File(TEST_FOLDER);
//        System.out.println(f);
////        System.out.println(f.list());
//        for (String name : f.list()) {
//            if (name.endsWith(".in")) {
//                try {
//                    File in = new File(TEST_FOLDER + "/" + name);
//                    File out = new File(TEST_FOLDER + "/" + name.substring(0, name.length() - 3) + ".ans");
//                    Scanner in_scan = new Scanner(in);
//                    Scanner out_scan = new Scanner(out);
//                    int n = in_scan.nextInt();
//                    int[] weights = new int[n];
//                    for (int i = 0; i < n; i++) {
//                        weights[i] = in_scan.nextInt();
//                    }
//                    int got = weight(weights);
//                    int expected = out_scan.nextInt();
//                    if (got != expected) {
//                        System.out.printf("Expected %d but got %d\n", expected, got);
//                        failedTests++;
//                    } else {
//                        System.out.printf("Passed test %s\n", name);
//                        passedTests++;
//                    }
//                    in_scan.close();
//                    out_scan.close();
//                } catch (FileNotFoundException e) {
//                    System.out.println(e);
//                }
//            }
//        }
//        System.out.printf("passed Tests: " + passedTests + "/3" + "         failed test:" + failedTests + "/3");
//    }
public static void main(String[] args) {
int[] plates = {666,667};
    System.out.println((weight(plates)));
}
//    static int 1000 = 1000;
//

    public static int weight(int[] plates) {
        LinkedList<Integer> range = new LinkedList<Integer>(); //will be used to store the range of weights possible

        if(plates.length==0) return 0;
        for (int plate : plates) { //iterate through the weights
            //add 1 weight

            int lastWeightAdded = plate;
            int sizeOfList = range.size();

            for (int i = 0; i < sizeOfList ; i++) {
                int previousElement = range.get(i);     //get the previous elements
                if(lastWeightAdded + previousElement == 1000) return 1000;      //if we get 100, then just return 1000
                if(plate<=1000) {
//                 if (range.contains(lastWeightAdded + previousElement)) ; //if the range
                     if ((lastWeightAdded + previousElement) < 1000 + 1000)
                        range.add(lastWeightAdded + previousElement); //add the last added element to all the previous ones
                }
            }
            if(plate<=1000)
            range.add(plate);
        }
        range.add(0);

        //we want to get the smallest interval between the plate and the desiredWeight
        int interval = Math.abs(range.get(0) - 1000);  //get the first interval
        int k = 0; //keeps track of which element is the closet to 1000
        for(int i = 1; i < range.size(); i++){  //iterate through the plates
            if((range.get(i) - 1000)>0) {  //if the plate's weight is bigger than the target
                int otherInterval = Math.abs(range.get(i) - 1000);  //calculate the interval of the next element
                if(otherInterval <= interval){  //if that other element is closer to the target
                    k = i;                     //then we set k to its index
                    interval = otherInterval;  //and now the current interval is that one
                }
            }
            else {
                int otherInterval =  Math.abs(range.get(i) - 1000);

                if (otherInterval < interval) {  //if that other element is closer to the target
                    k = i;                     //then we set k to its index
                    interval = otherInterval;  //and now the current interval is that one
                }
            }
            System.out.println("the closest weight is:   "+range.get(k));
        }
        int closestWeight = range.get(k);
        return closestWeight;
    }
}



