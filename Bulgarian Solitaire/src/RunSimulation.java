// Name: Uche Uba
// USC NetID: uuba
// CSCI455 PA2
// Spring 2018

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

    /** This class is responsible for playing a round of bulgarian solitaire, based on the specified step
    */
public class RunSimulation {

    public RunSimulation (){

    }

    Scanner keyin= new Scanner(System.in);


    /**
     * This method simulates a round of the bulgarian solitaire game, and either
     * returns an output in form of a single step until the game is done, or plays the
     * game without any interference form the user
     * @param board creates a SolitaireBoard object that will either take input from the user, or generate it's own
     * @param step accounts for whether the output should be inform of a single step or not
     */

    public void playRound (SolitaireBoard board, boolean step){

        if (step==true){
            int count = 1;
            while(!board.isDone()){
                board.playRound();
                System.out.println("["+ count++ +"]" + "Current configuration: "+ board.configString());
                System.out.print("<Type return to continue>");
                keyin.nextLine();

                if(board.isDone())
                    System.out.println("Done!");
            }

        }

        else {
            int count= 1;
            while (!board.isDone()) {
                board.playRound();
                System.out.println("["+ count++ +"]" + "Current configuration: "+ board.configString());
            }
            System.out.println("Done!");
        }
    }

        /**
         * Mehtod checks if the user input satisfies representation invariants, and returns true iff it does
         * @param list array list of input from the user
         * @return boolean that checks iput validity
         */

    public boolean validBoardInput(ArrayList<Integer> list){
        Iterator<Integer> itr1= list.iterator();
        int sum=0;
        while (itr1.hasNext()){
            sum+= itr1.next();
        }

        Iterator<Integer> itr2= list.iterator();
        while(itr2.hasNext()){
            if(list.size()>0 && list.size()<SolitaireBoard.CARD_TOTAL){
                int num= itr2.next();
                if (num<0 || num>SolitaireBoard.CARD_TOTAL|| num==0 || sum>SolitaireBoard.CARD_TOTAL
                        || sum<SolitaireBoard.CARD_TOTAL) {
                    return false;
                }
            }

        }

        return true;
    }

}
