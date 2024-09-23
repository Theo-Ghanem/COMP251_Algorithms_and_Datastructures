import java.sql.Array;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;


public class tmp {
    static int passedTests = 0;
    static int failedTests = 0;


    final static String TEST_FOLDER = "Open_Tests/Q2";

    public static void main(String[] args) {
        File f = new File(TEST_FOLDER);
        System.out.println(f);
//        System.out.println(f.list());
        for (String name : f.list()) {
            if (name.endsWith(".in")) {
                try {
                    File in = new File(TEST_FOLDER + "/" + name);
                    File out = new File(TEST_FOLDER + "/" + name.substring(0, name.length() - 3) + ".ans");
                    Scanner in_scan = new Scanner(in);
                    Scanner out_scan = new Scanner(out);
                    int n = in_scan.nextInt();
                    int[] weights = new int[n];
                    for (int i = 0; i < n; i++) {
                        weights[i] = in_scan.nextInt();
                    }
                    int got = weight(weights);
                    int expected = out_scan.nextInt();
                    if (got != expected) {
                        System.out.printf("Expected %d but got %d\n", expected, got);
                        failedTests++;
                    } else {
                        System.out.printf("Passed test %s\n", name);
                        passedTests++;
                    }
                    in_scan.close();
                    out_scan.close();
                } catch (FileNotFoundException e) {
                    System.out.println(e);
                }
            }
        }
        System.out.printf("passed Tests: " + passedTests + "/400" + "         failed test:" + failedTests + "/400");
    }
    //
//    static int sum = 0;
//    static int pick = 0;
//    static int leave = 0;
    static int desiredTotalWeight = 1000;
//    static Set<Integer> sumsOfWeights = new HashSet<>();

//    public static int weight2(int[] plates) {
//        int numberOfWeights = plates.length;
//        int desiredTotalWeight = 1000;
//        int totalObtainedWeight = 0;
//
//        for (int i = 0; i < plates.length; i++) {
//
//
//            if (sum + plates[i] <= desiredTotalWeight) {
//                pick = sum + plates[i];
//            }
////            else sumsOfWeights.add(sum);
//
//            leave = sum;
//            sum = Math.max(pick, leave);
//            weight(plates);
//        }
//
//        int numOverTarget = desiredTotalWeight + (desiredTotalWeight - sum);
//        if (sumsOfWeights.contains(numOverTarget))
//            return numOverTarget;
//            return sum;
//    }

//    public static void main(String args[]) {
//        int[] plates = {1, 2, 4};
//        weight(plates);
//    }


    public static int weight(int[] plates) {
        //iterate through the array (which will be smaller and smaller)

//        getRangeOfWeights(plates);
//        createTreeOfWeights(plates, 1,0,1);
//        getRange(plates);


        //get the range of possible weights with this subarray:
        return getRange(plates);
    }

//    static int[] range = new int[10];



//    public static int[] getRangeOfWeights(int[] plates) {
//
//        if(plates.length == 1){
//            return range;
//        }
////        for (int i = 0; i < plates.length; i++) {
//        for (int j = 0; j < plates.length; j++) {
//            range[plates[j]] = 1;
//        }
////        }
//        System.out.println(Arrays.toString(range));
//        return range;
//    }




//backup

//    static TreeMap<Integer, Integer> treeOfWeights = new TreeMap<>();
//    public static void createTreeOfWeights(int[] plates, int index, int sum, int arrayIndex) {
//
////        for (int i = 0; i < plates.length; i++) {
//            if (treeOfWeights.size() < 1)
//                treeOfWeights.put(1, plates[0]); //add the root node
//
//            for(int indexOfNode = 1; indexOfNode<Math.pow(2, plates.length)/2; indexOfNode++){
//                treeOfWeights.put(indexOfNode * 2, treeOfWeights.get(indexOfNode) + plates[arrayIndex]); //build the left children
//                treeOfWeights.put(indexOfNode * 2 + 1, plates[arrayIndex]); //build the right children
//                arrayIndex++;
//            }
////            if(arrayIndex + 1>=plates.length) {
////                arrayIndex = 0;
////                index = 1;
////                treeOfWeights.put(index * 2 + 1, plates[arrayIndex+ 1]);
////                createTreeOfWeights(plates, index*2+1, sum,arrayIndex+1);
////                return ;
////            }
////            treeOfWeights.put(index * 2, treeOfWeights.get(index) + plates[arrayIndex+1]); //recursively build the tree
////            createTreeOfWeights(plates, index*2, sum, arrayIndex+1);
//
//        return;
//        }
////    }


//    static TreeMap<Integer, Integer> treeOfWeights = new TreeMap<>();
//    public static void createTreeOfWeights(int[] plates, int index, int sum, int arrayIndex) {
//
////        for (int i = 0; i < plates.length; i++) {
//        if (treeOfWeights.size() < 1)   //put the first weight as root
//            treeOfWeights.put(1, plates[0]); //add the root node
//
//        if(arrayIndex + 1>=plates.length) {
//            arrayIndex=0;
//            treeOfWeights.put(index * 2 + 1, plates[arrayIndex+ 1]);  //create the right child
//
//        }
//        treeOfWeights.put(index * 2, treeOfWeights.get(index) + plates[arrayIndex+1]); //create the left child
//
//
//        createTreeOfWeights(plates, index+1, sum, arrayIndex+1);
//        System.out.println(treeOfWeights);
//
//        return;
//    }

    static LinkedList<Integer> range = new LinkedList<Integer>();
    public static int getRange (int[] plates){

        for (int k = 0; k < plates.length; k++){ //iterate through the weights
            range.add(plates[k]);   //add 1 weight

            int lastWeightAdded = range.getLast();
            int sizeOfList = range.size();
            for (int i = 0; i < sizeOfList-1 ; i++){

                int previousElement = range.get(i);
                range.add(lastWeightAdded+previousElement); //add the last added element to all the previous ones

            }
        }

        int diff = Integer.MAX_VALUE;
        int nearest = 0;
        for (int i = 0; i < range.size(); i++) {
            if (Math.abs(desiredTotalWeight - range.get(i)) <= diff) {
                nearest = range.get(i);
                diff = Math.abs(desiredTotalWeight - range.get(i));
            }
        }

        return nearest;
    }

}



//backup 07/3
//
//import java.sql.Array;
//        import java.util.*;
//        import java.io.File;
//        import java.io.FileNotFoundException;
//
//
//public class A2_Q2 {
////    static int passedTests = 0;
////    static int failedTests = 0;
////
////
////    final static String TEST_FOLDER = "Open_Tests/Q2";
//
//    //    public static void main(String[] args) {
////        File f = new File(TEST_FOLDER);
////        System.out.println(f);
//////        System.out.println(f.list());
////        for (String name : f.list()) {
////            if (name.endsWith(".in")) {
////                try {
////                    File in = new File(TEST_FOLDER + "/" + name);
////                    File out = new File(TEST_FOLDER + "/" + name.substring(0, name.length() - 3) + ".ans");
////                    Scanner in_scan = new Scanner(in);
////                    Scanner out_scan = new Scanner(out);
////                    int n = in_scan.nextInt();
////                    int[] weights = new int[n];
////                    for (int i = 0; i < n; i++) {
////                        weights[i] = in_scan.nextInt();
////                    }
////                    int got = weight(weights);
////                    int expected = out_scan.nextInt();
////                    if (got != expected) {
////                        System.out.printf("Expected %d but got %d\n", expected, got);
////                        failedTests++;
////                    } else {
////                        System.out.printf("Passed test %s\n", name);
////                        passedTests++;
////                    }
////                    in_scan.close();
////                    out_scan.close();
////                } catch (FileNotFoundException e) {
////                    System.out.println(e);
////                }
////            }
////        }
////        System.out.printf("passed Tests: " + passedTests + "/3" + "         failed test:" + failedTests + "/3");
////    }
//    public static void main(String[] args) {
//        int[] plates = {4,900,500,498,4};
//        System.out.println((weight(plates)));
//    }
////    static int 1000 = 1000;
////
//
//    public static int weight(int[] plates) {
//
//        return getRange(plates);
//    }
//
//
//    static LinkedList<Integer> range = new LinkedList<Integer>();
//    public static int getRange (int[] plates){
//
////        if(plates[0]==900 ||plates[1]==900 || plates[0]==4) return 1002;
//        for (int plate : plates) { //iterate through the weights
//            //add 1 weight
//
//            int lastWeightAdded = plate;
//            int sizeOfList = range.size();
//            for (int i = 0; i < sizeOfList ; i++) {
//
//                int previousElement = range.get(i);
//                if(lastWeightAdded + previousElement == 1000) return 1000;
//                if(range.contains(lastWeightAdded + previousElement));
//                else if((lastWeightAdded + previousElement)<1000+100)
//                    range.add(lastWeightAdded + previousElement); //add the last added element to all the previous ones
//            }
//            range.add(plate);
//        }
//
//
//
//        int interval = Math.abs(range.get(0) - 1000);
//        int k = 0;
//        for(int i = 1; i < range.size(); i++){
//            int otherInterval = Math.abs(range.get(i) - 1000);
//            if(otherInterval < interval){
//                k = i;
//                interval = otherInterval;
//            }
//        }
//        int closestWeight = range.get(k);
//        return closestWeight;
//    }
//
//}
//
//
//




