//no collaborators
import java.math.BigInteger;
import java.util.HashMap;


public class A2_Q4 {
    /**
     *
     * @param N represents the fibonacci number we want to calculate
     * @param K index of letter
     * @return "X" or "Y"
     */
    public static String mod_fibonacci(int N, BigInteger K) {
        BigInteger numberOfLetters = fib(N);

        if(N==1) return "X";
        while(numberOfLetters.compareTo(BigInteger.valueOf(2))!=-1) {
            int comparison = K.compareTo(fibMap.get(N-2));
            if(N==3) {
                if (K.compareTo(BigInteger.valueOf(1)) == 0) return "X";
                if (K.compareTo(BigInteger.valueOf(2)) == 0) return "Y";
            }

            if (comparison == 0) { // K is equal to the result of fib(N-2) --> so the letter is in the fib(N-2)
                numberOfLetters = fibMap.get(N - 2);
                N = N - 2;
            }
            if (comparison == 1) { // K is greater than the result of fib(N-2) --> so the letter is in the fib(N-1)
                numberOfLetters = fibMap.get(N - 1);
                K = K.subtract(fibMap.get(N - 2));
                N = N - 1;

            } else { // K is smaller than the result of fib(N-2) --> so the letter is in the fib(N-2)
                numberOfLetters = fibMap.get(N - 2);
                N = N - 2;
            }
        }
        return "Y";
    }


    public static void main(String[] args) {
        mod_fibonacci(1, new BigInteger("1"));
    }

    static HashMap<Integer,BigInteger> fibMap = new HashMap<Integer, BigInteger>(); //Store the calculated Fibonacci String in the fibTree

    static BigInteger fib(int n) {
        BigInteger a = BigInteger.valueOf(0);
        fibMap.put(0,a);
        BigInteger b = BigInteger.valueOf(1);
        fibMap.put(1,b);
        BigInteger c = BigInteger.valueOf(1);
        for (int i=2 ; i<=n ; i++) {
            c =  a.add(b);  //add the fib number to the fibTree

            fibMap.put(i,c);
            a = b;
            b = c;
        }
        return (b);
    }
}