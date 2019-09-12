// Name: Uche Uba
// USC NetID: uuba
// CSCI455 PA2
// Spring 2018

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/*
   class SolitaireBoard
   The board for Bulgarian Solitaire.  You can change what the total number of cards is for the game
   by changing NUM_FINAL_PILES, below.  Don't change CARD_TOTAL directly, because there are only some values
   for CARD_TOTAL that result in a game that terminates.
   (See comments below next to named constant declarations for more details on this.)
 */


public class SolitaireBoard {
   
   public static final int NUM_FINAL_PILES = 9;
   // number of piles in a final configuration
   // (note: if NUM_FINAL_PILES is 9, then CARD_TOTAL below will be 45)
   
   public static final int CARD_TOTAL = NUM_FINAL_PILES * (NUM_FINAL_PILES + 1) / 2;
   // bulgarian solitaire only terminates if CARD_TOTAL is a triangular number.
   // see: http://en.wikipedia.org/wiki/Bulgarian_solitaire for more details
   // the above formula is the closed form for 1 + 2 + 3 + . . . + NUM_FINAL_PILES

    // Note to students: you may not use an ArrayList -- see assgt description for details.
   
   
   /**
      Representation invariant:
      1)Total_num_piles == CARD_TOTAL;
      2)Initial pile configuration >0 && int
      3)num_curr_piles <= arr.length
      4)num_curr_piles > 0

    */
   
   // <add instance variables here>
    private Integer[] arr = new Integer[45];
    private int curr_num_piles = 0;                // Keeps track of the current number of piles after each iteration
    private Random obj;
 
   /**
     Creates a solitaire board with the configuration specified in piles.
     piles has the number of cards in the first pile, then the number of cards in the second pile, etc.
     PRE: piles contains a sequence of positive numbers that sum to SolitaireBoard.CARD_TOTAL
   */
   public SolitaireBoard(ArrayList<Integer> piles) {

         Arrays.fill(arr, 0);           // fills the integer array with zeroes
         arr= piles.toArray(arr);
         curr_num_piles= piles.size();

      assert isValidSolitaireBoard();   // sample assert statement (you will be adding more of these calls)
   }
 
   
   /**
      Creates a solitaire board with a random initial configuration.
   */
   public SolitaireBoard() {

       obj= new Random();
       Arrays.fill(arr, 0);           // fills the integer array with zeroes
       int pile= 0;

       int firstpile= obj.nextInt(CARD_TOTAL+1);
       if(firstpile==0){
           firstpile= 1;
       }
       arr[pile]= firstpile;
       int cardsleft= CARD_TOTAL-firstpile;

       while(cardsleft>0){
           int nextpile= obj.nextInt(cardsleft+1);
           if(nextpile==0) {
               nextpile += 1;
           }
           pile++;
           arr[pile]= nextpile;
           cardsleft-= nextpile;
           curr_num_piles= pile+1;
       }

       assert isValidSolitaireBoard();
   }

   /**
      Plays one round of Bulgarian solitaire.  Updates the configuration according to the rules
      of Bulgarian solitaire: Takes one card from each pile, and puts them all together in a new pile.
      The old piles that are left will be in the same relative order as before, 
      and the new pile will be at the end.
    */
   public void playRound() {

       int count= 0, num_zeroes= 0;

       for (int a=0; a<curr_num_piles; a++){       // decrements each pile by one
           arr[a]= arr[a]-1;
           count++;
           if(arr[a]==0){
               num_zeroes++;
           }
       }
       arr[curr_num_piles]= count;                // board has new pile which is sum of old piles decremented
       curr_num_piles++;

       if(num_zeroes>0) {
           removeZeroes(arr);
       }

       assert isValidSolitaireBoard();
   }
   
   /**
      Returns true iff the current board is at the end of the game.  That is, there are NUM_FINAL_PILES
      piles that are of sizes 1, 2, 3, . . . , NUM_FINAL_PILES, in any order.
    */

   public boolean isDone() {

       if(contains(arr,1) && contains(arr,2) && contains(arr,3) && contains(arr,4)
       && contains(arr,5) && contains(arr,6) && contains(arr,7) && contains(arr,8)
       && contains(arr,9)){
        return true;
       }

       assert isValidSolitaireBoard();

       return false;
   }

   
   /**
      Returns current board configuration as a string with the format of
      a space-separated list of numbers with no leading or trailing spaces.
      The numbers represent the number of cards in each non-empty pile.
    */
   public String configString() {

       int[] temparr= new int[curr_num_piles];

       for(int i= 0; i<curr_num_piles; i++){
           temparr[i]= arr[i];
       }

       String str= Arrays.toString(temparr).replace("[","")
                    .replace("]","").replace(",","").trim();


       assert isValidSolitaireBoard();

       return str;
   }
   
   
   /**
      Returns true iff the solitaire board data is in a valid state
      (See representation invariant comment for more details.)
    */
   private boolean isValidSolitaireBoard() {

       int numpiles= totalNumcards(arr);

       for (int i=0; i<curr_num_piles; i++) {
           if (curr_num_piles < arr.length && curr_num_piles> 0){
               if(numpiles != CARD_TOTAL || arr[i] ==0 )
                   return false;
           }
       }

      return true;
   }

    /**
        Removes the zeroes from the board after each round if there are any, by pushing them to the end of the array
        and reducing the number of piles
        @param arr1 is the given board configuration
     */
    private void removeZeroes(Integer[] arr1){

       int j=0;
       for (int i=0; i<arr1.length; i++){
           if(arr1[i]!=0){
               arr1[j]= arr1[i];
               j++;
           }
       }
       curr_num_piles= j;

       while(j<arr1.length){
           arr1[j++]= 0;
       }
    }

    /**
        Checks if an array contains a given element, and returns true iff it does
        @param array is the board configuration in question
        @param n is the value being searched for
     */
    private boolean contains (Integer[] array, int n){

        for (int x= 0; x<curr_num_piles; x++){
            if (array [x] == n){
                return true;
            }
        }
        return false;
    }

    private int totalNumcards(Integer[] array){
        int sum= 0;

        for(int i=0; i<curr_num_piles; i++){
            sum+= array[i];
        }
        return sum;
    }


}
