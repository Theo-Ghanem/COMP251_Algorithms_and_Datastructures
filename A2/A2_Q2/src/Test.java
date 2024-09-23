//import java.util.Arrays;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//public class Test {
//    public static void main(String[] args) {
//        int K = 1000;
//        List<Integer> inputs = Arrays.asList(900,
//                500,
//                498,
//                4
//        );
//
//        int opt = 0;                // optimal solution so far
//
//        Set<Integer> sums = new HashSet<>();
//        sums.add(opt);
//
//        // loop over all input values
//        for (Integer input : inputs) {
//            Set<Integer> newSums = new HashSet<>();
//
//            // loop over all sums so far
//            for (Integer sum : sums) {
//                int newSum = sum + input;
//
//                // ignore too big sums
//                if (newSum <= K) {
//                    newSums.add(newSum);
//
//                    // update optimum
//                    if (newSum > opt) {
//                        opt = newSum;
//                    }
//                }
//            }
//
//            sums.addAll(newSums);
//        }
//
//        System.out.println(opt);
//    }
//}

//dp v1

//public static int weight(int[] plates) {
//        int numberOfWeights = plates.length;
//        int desiredTotalWeight = 1000;
//        int totalObtainedWeight = 0;
//        int[][] dp = new int[numberOfWeights][desiredTotalWeight];
//
//        for(int sum=0; sum<desiredTotalWeight; sum++) {
//        dp[numberOfWeights][sum] = sum;
//        }
//
//        for(int i=numberOfWeights-1; i>=0; i--){
//        for (int sum=desiredTotalWeight-1; sum>=0; sum--) {
//        int pick = 0;
//        if (sum + plates[i] <= desiredTotalWeight) {
//        pick = dp[i + 1][sum + plates[i]];
//        }
//        int leave = dp[i + 1][sum];
//        dp[i][sum] = Math.max(pick, leave);
//        }
//        }
//        totalObtainedWeight = dp[0][0];
//
//        return totalObtainedWeight;
//        }

//backtracking:

//public static int weight(int[] plates) {
//        int desiredTotalWeight = 1000;
//
//        int totalObtainedWeight =  backtrack(0,0,plates);
//        int numOverTarget = desiredTotalWeight+(desiredTotalWeight-totalObtainedWeight);
//        if(sumsOfWeights.contains(numOverTarget)){
//        return numOverTarget;
//        }
//        return totalObtainedWeight;
//
//
//        }
//
//static Set<Integer> sumsOfWeights = new HashSet<>();
//public static int backtrack(int i, int sum, int[] plates) {
//        System.out.println("iteration: "+i);
//        int numberOfWeights = plates.length;
//        int desiredTotalWeight = 1000;
//
//
//        if(sum>desiredTotalWeight){
//        sumsOfWeights.add(sum);
//        return 0;
//        }
//        if(i == numberOfWeights){
//        sumsOfWeights.add(sum);
//        return sum;
//        }
//        int takePlate = backtrack(i+1, sum + plates[i], plates);
//        int leavePlate = backtrack(i+1, sum, plates);
//
//        int totalObtainedWeight = Math.max(takePlate,leavePlate);
//
//        return totalObtainedWeight;
//
//        }