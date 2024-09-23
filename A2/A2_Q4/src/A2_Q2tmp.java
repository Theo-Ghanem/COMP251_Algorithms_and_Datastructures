//import java.math.BigInteger;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.Scanner;
//
//public class A2_Q4 {
//
//    public static String mod_fibonacci(int N, BigInteger K) {
//        BigInteger fibNumber = fib(N);
//
//        int comparison = K.compareTo(fibMap.get(N-2));
//
//        while(fibNumber.compareTo(BigInteger.valueOf(2))!=-1) {
//            if (comparison == 0) { // K is equal to the result of fib(N-2) --> so the letter is in the fib(N-2)
//                fibNumber = fibMap.get(N - 2);
//                N = N - 2;
//            }
//            if (comparison == 1) { // K is greater than the result of fib(N-2) --> so the letter is in the fib(N-1)
//                fibNumber = fibMap.get(N - 1);
//                N = N - 1;
//            } else { // K is smaller than the result of fib(N-2) --> so the letter is in the fib(N-2)
//                fibNumber = fibMap.get(N - 2);
//                N = N - 2;
//            }
//        }
//
//        if(N==2){
//            System.out.println("answer is Y !");
//            return "Y";
//        }
//        System.out.println("answer is X !");
//        return "X";
//    }
//
//
//    public static void main(String[] args) {
//
//        mod_fibonacci(7,BigInteger.valueOf(7));
//
//
//    }
//
//    static HashMap<Integer,BigInteger> fibMap = new HashMap<Integer, BigInteger>(); //Store the calculated Fibonacci String in the fibTree
//    static BigInteger fib(int n) {
////        BigInteger[] fibArray = new BigInteger[(int) (n+1)]; //Store the calculated Fibonacci Strings.
////        fibArray[0] = BigInteger.valueOf(0); // Base Case 1
////        fibArray[1] = BigInteger.valueOf(1); // Base Case 1
//        fibMap.put(0,BigInteger.valueOf(0));
//        fibMap.put(1,BigInteger.valueOf(1));
//
//        BigInteger a = BigInteger.valueOf(0);
//        BigInteger b = BigInteger.valueOf(1);
//        BigInteger c = BigInteger.valueOf(1);
//        for (int i=2 ; i<=n ; i++) {
//            c =  a.add(b);  //add the fib number to the fibTree
////            fibArray[i] = c; //add the fib number to the fibArray
//            fibMap.put(i,c);
//            a = b;
//            b = c;
//        }
////        System.out.println(b);
////        System.out.println(fibArray[fibArray.length-1]);
//        return (b);
//    }
//
//    static BigInteger fib2(int n) {
//        BigInteger fibArray[] = new BigInteger[(int) (n+1)]; //Store the calculated Fibonacci Strings.
//        fibArray[0] = BigInteger.valueOf(0);; // Base Case 1
//        fibArray[1] = BigInteger.valueOf(1);; // Base Case 1
//
//        BigInteger a = BigInteger.valueOf(0);
//        BigInteger b = BigInteger.valueOf(1);
//        BigInteger c = BigInteger.valueOf(1);
//        for (int i=2 ; i<=n ; i++) {
//            c =  a.add(b);
//            fibArray[i] = c;
//            a = b;
//            fibArray[i-2] = a;
//            b = c;
//            fibArray[i-1] = b;
//        }
//        System.out.println(b);
//        System.out.println(fibArray[fibArray.length-1]);
//        return (b);
//    }
//
//
//    //NOT CORRECT
////	public static BigInteger fibonacci(long n)  {
////
////        BigInteger fibArray[] = new BigInteger[(int) (n+1)]; //Store the calculated Fibonacci Strings.
////
////		fibArray[1] = BigInteger.valueOf(0);; // Base Case 1
////		fibArray[2] = BigInteger.valueOf(1);; // Base Case 1
////
////		for (int i = 3; i <= n; i++) {
////            BigInteger a =fibArray[i-2];
////            BigInteger b =fibArray[i-1];
////
////                    fibArray[i] = a.add(b); //add the previous 2 elements in the array and store in the next slot
////		}
//////		System.out.println(Arrays.toString(fibArray));
////        System.out.println(fibArray[fibArray.length-1]);
////		return fibArray[(int)n];
////	}
//
////	static String fib(int n)
////	{
////		String a = "X", b = "Y", c;
////		if (n == 0)
////			return a;
////		for (int i = 2; i <= n; i++)
////		{
////			c = a + b;
////			a = b;
////			b = c;
////		}
////		System.out.println(b);
////		return b;
////	}
//
//
//
////	public static String fibonacci(int n)  {
////		if(n == 1)
////			return "X";
////		else if(n == 2)
////			return "Y";
////		else
////			return fibonacci(n - 2) + fibonacci(n - 1);
////	}
//
////	public static String fibonacci(int n, ){
////		long fibValue=0;
////		if(n==0 ){
////			return "X";
////		}else if(n==1){
////			return "Y";
////		}else if(fibArray[(int)n]!=0){
////			return fibArray[(int)n];
////		}else{
////			fibValue=fibonacci(n-1)+fibonacci(n-2);
////			fibArray[(int) n]=fibValue;
////			return fibValue;
////		}
////	}
//
//
//
//
//}
