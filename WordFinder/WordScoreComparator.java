// Name: Uche Uba
// USC NetID: uuba
// CS 455 PA4
// Spring 2018

import java.util.Comparator;

/**
 * A class the compares two anagrams based on their scrabble score. Anagrams with higher scores come first
 * and if theu have the same scores, then they are compared based on alphabetical ordering.
 */
public class WordScoreComparator implements Comparator<WordScore> {

    /**
     * a comes before b in the ordering if a's score is bigger
     * returns negative if a's score is bigger than b's score, and
     * positive ig b's score is bigger. If the scores are the same,
     * then compareTo is used to compare them in terms of alphabetical ordering
     * @param a object of type WordScore
     * @param b object of type WordScore
     * @return an integer that indicates how they will be ordered
     */
    public int compare (WordScore a, WordScore b){
        int val= b.getScore()-a.getScore();
        if(val==0){
            return a.getScrabbleWord().compareTo(b.getScrabbleWord());
        }

        else
            return val;
    }
}
