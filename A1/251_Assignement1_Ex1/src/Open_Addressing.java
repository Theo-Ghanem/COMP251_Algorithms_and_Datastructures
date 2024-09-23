import java.io.*;
import java.util.*;

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
     public  int insertKey(int key){
         int attempt = 0;                           //initialize attempt to 0
         while (attempt<m){                         //don't attempt more than the number of buckets
             int j = probe(key, attempt);           //get the hashvalue
//             if (Table[j] == key) return attempt;   //if the slot contains the key return the number of attempts
             if (Table[j] < 0){                     //if the slot has a -1 then it is empty, if it has a -2 then it's previous value was deleted
                 Table[j] = key;                    //so we can store the key in that slot
                 return attempt;                  //return the number of attempts it took before storing the key
             }
             else ++attempt;                        //if we haven't found an empty slot attempt again
         }
         System.out.println(attempt);
         return attempt;                            //returns number of attempts it took before giving up
     }
        
        /**Sequentially inserts a list of keys into the HashTable. Outputs total number of collisions */
        public int insertKeyArray (int[] keyArray){
            int collision = 0;
            for (int key: keyArray) {
                collision += insertKey(key);
            }
            return collision;
        }
            
         /**Remove key k into hash table. Returns the number of collisions encountered*/
         public int removeKey(int key){
             int attempt = 0;                   //initialize attempt to 0
             while (attempt<m){                 //don't attempt more than the number of buckets
                 int j = probe(key, attempt);   //get the hashvalue
                 if (Table[j] == key) {         //if the key is found
                     Table[j] = -2;             //delete it by setting it equal to -2
                     return attempt;            //return the number of attempts
                 }
                 else if (Table[j] == -2)       //if the slot had a value that was deleted
                     ++attempt;                 //attempt again to find it
                 else if (Table[j] == -1)       //if the slot is empty then there never was a value in this slot
                     return ++attempt;          //return the number of attempts
                 else  ++attempt;               //attempt again to find the key
             }
             return attempt;                    //returns number of attempts it took before giving up
         }
}
