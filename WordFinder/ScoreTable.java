// Name: Uche Uba
// USC NetID: uuba
// CS 455 PA4
// Spring 2018

/**
 * Has information about scrabble scores for letters and words. Has information to calculate the score
 * for any word or subset of a word derived from the rack
 */

public class ScoreTable {
    //An array of int that contains the score of each character in the alphabet indexed by the character
    //int value -'a'
    private static final int[] letterScore= {1,3,3,2,1,4,2,4,1,8,5,1,3,1,1,3,10,1,1,1,1,4,4,8,4,10};

    public ScoreTable(){
    }

    /**
     * gets the score of a character from the letterscore array
     * @param letter character
     * @return a value of the character
     */
    public int getScrabbleScore(char letter){
        if (Character.isUpperCase(letter)){
            return letterScore[letter - 'A'];
        }
        return letterScore[letter - 'a'];
    }

    /**
     * Calculates the score of a word using the information from the array of character values
     * @param letters is the word that get it's score calculated
     * @return the total score calculated from the summation of all character values in the word
     */
    public int calculateScore(String letters){
        int score= 0;

        for(int i=0; i<letters.length(); i++){
            score+= getScrabbleScore(letters.charAt(i));
        }

        return score;
    }


}
