// Name: Uche Uba
// USC NetID: 9239603212
// CS 455 PA1
// Spring 2018

import java.util.*;
/**
 * class src.CoinTossSimulator
 * 
 * Simulates trials of repeatedly tossing two coins and allows the user to access the
 * cumulative results.
 * 
 * NOTE: we have provided the public interface for this class.  Do not change
 * the public interface.  You can add private instance variables, constants, 
 * and private methods to the class.  You will also be completing the 
 * implementation of the methods given. 
 * 
 * Invariant: getNumTrials() = getTwoHeads() + getTwoTails() + getHeadTails()
 * 
 */
public class CoinTossSimulator {

   private int Total_trials= 0;
   private final int HEADS= 1;
   private final int TAILS= 0;
   private int HeadsHeads= 0;
   private int TailsTails= 0;
   private int HeadsTails= 0;
   private int trials = 0;
   private int Total_HH= 0;
   private int Total_TT= 0;
   private int Total_HT= 0;


   /**
      Creates a coin toss simulator with no trials done yet.
   */
   public CoinTossSimulator() {

   }


   /**
      Runs the simulation for numTrials more trials. Multiple calls to this method
      without a reset() between them *add* these trials to the current simulation.
      
      @param numTrials  number of trials to for simulation; must be >= 1
    */
   public void run(int numTrials) {

       Random Coin1 = new Random();
       Random Coin2= new Random();

       while ( trials < numTrials ) {
           int flip = Coin1.nextInt(2);
           int flip2 = Coin2.nextInt(2);

           if (flip == HEADS && flip2 == HEADS) {
               HeadsHeads++;
           } else if (flip == TAILS && flip2 == HEADS) {
               HeadsTails++;
           } else if (flip == HEADS && flip2 == TAILS) {
               HeadsTails++;
           } else {
               TailsTails++;
           }
           trials ++;
       }
       Total_trials += trials;
       Total_HT += HeadsTails;
       Total_HH += HeadsHeads;
       Total_TT += TailsTails;
   }


   /**
      Get number of trials performed since last reset.
   */
   public int getNumTrials() {

       return Total_trials;
   }


   /**
      Get number of trials that came up two heads since last reset.
   */
   public int getTwoHeads() {

       return Total_HH;
   }


   /**
     Get number of trials that came up two tails since last reset.
   */  
   public int getTwoTails() {

       return Total_TT;
   }


   /**
     Get number of trials that came up one head and one tail since last reset.
   */
   public int getHeadTails() {

       return Total_HT;
   }

   /**
     Checks that the invariant is valid.
    */
   public boolean isValidSum(){

       if(getNumTrials()== getTwoHeads()+getTwoTails()+getHeadTails()){
           return true;
       }

       else {
           return false;
       }
   }


   /**
      Resets the simulation, so that subsequent runs start from 0 trials done.
    */
   public void reset() {
       Total_trials= 0;
       Total_HH= 0;
       Total_HT= 0;
       Total_TT= 0;
   }

}
