// Name: Uche Uba
// USC NetID: uuba
// CSCI455 PA2
// Spring 2018

import java.util.*;
import java.lang.*;

/**
      This class simulates a game of bulgarian solitaire, where an initial configuration of card piles is
   continually decreased by 1, increased by the total number of piles decreased, and pile configuration
   shifted until the final configuration increases from one to the final number of piles.
      This method had 2 arguments: Single step, which either requires the user to hit return before generating
   a new configuration, or runs the whole simulation without user input; userConfig, which either requires an
   input from the user, or randomly generates an initial configuration of the board to play
 */

public class BulgarianSolitaireSimulator {

   public static void main(String[] args) {


       boolean singleStep = false;
       boolean userConfig = false;


       for (int i = 0; i < args.length; i++) {
           if (args[i].equals("-u")) {
               userConfig = true;
           } else if (args[i].equals("-s")) {
               singleStep = true;
           }
       }

       playConfig(userConfig, singleStep);

   }

    /**
     * This method runs the bulgarian solitaire simulation based on what arguments are used
     * @param config User configuration that either has the program prompt the user for a valid input, or randomly
     *               generates one
     * @param step  Decides if the output will be displayed after the user hits return, or after the program is
     *              done running the game
     */
   private static void playConfig(boolean config, boolean step){

       RunSimulation run = new RunSimulation();

       // Checks if user config is true, and simulates the game based on that
       if (config == true) {

           Scanner in = new Scanner(System.in);
           Scanner parser;
           String tmp;

           ArrayList<Integer> piles = new ArrayList<>();
           System.out.println("Number of total cards is 45");
           System.out.println("You will be entering the initial " +
                               "configurations of the cards (i.e., how many in each pile).");
           System.out.print("Please enter a space separated list of positive integers followed by newline: ");
           if (in.hasNextLine()) {
               tmp = in.nextLine();
               parser = new Scanner(tmp);
               while (parser.hasNext()) {
                   if (!parser.hasNextInt()) {
                       displayErrorMessage();
                       piles.clear();
                       tmp = in.nextLine();
                       parser = new Scanner(tmp);
                   } else {
                       piles.add(parser.nextInt());
                   }
               }

           }

           while (!run.validBoardInput(piles)) {       // Checks if input is valid
               displayErrorMessage();
               piles.clear();
               tmp = in.nextLine();
               parser = new Scanner(tmp);

               while (parser.hasNextInt()) {
                   piles.add(parser.nextInt());
               }

           }

           //Creates a solitaire board which takes input from the user
           SolitaireBoard userinput = new SolitaireBoard(piles);
           System.out.println("Initial configuration: " + userinput.configString());
           run.playRound(userinput, step);
       }

       /**
        * This version of the board generates a random initial configuration that meets the representation
        * invariant requirements
        */
       else {
           SolitaireBoard auto = new SolitaireBoard();
           System.out.println("Initial configuration: " + auto.configString());
           run.playRound(auto, step);

       }

   }

    /**
     * Error mesage displayed when user inputs an invalid board configuration
     */
   private static void displayErrorMessage() {
       System.out.println("Error each pile must have at least one card and the total number of cards must be 45");
       System.out.print("Please enter a space separated list of positive integers followed by a new line: ");
   }

}
