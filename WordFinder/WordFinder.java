// Name: Uche Uba
// USC NetID: uuba
// CS 455 PA4
// Spring 2018

import java.io.FileNotFoundException;
import java.util.*;

public class WordFinder {

    private static ArrayList<String> anagramList= new ArrayList<>();
    private static AnagramDictionary ad;
    private static ScoreTable scrabbleScores;

    /**
     * Uses either a default dictionary, or one from the user to find the anagrams of a word.
     * Creates a rack object which processes the word input frim a user, and finds all its subsets
     * it then finds the anagrams of each subset if they exist, and a score associated with the anagrams
     * it prints the result in descending order of their score, and ascending order of words if they have
     * the same score
     * @param args dictionary to be processed
     */
    public static void main (String[] args){

        String fileName;
        try {
            if(args.length<1){
                fileName= "sowpods.txt";
                ad = new AnagramDictionary(fileName);
            }
            else {
                fileName = args[0];
                ad = new AnagramDictionary(fileName);
            }

            System.out.println("Type . to quit.");     //Sentinel used to stop rack procession
            boolean sentinel= false;

            while(!sentinel) {   //Continues loop until termination sentinel is entered
                anagramList.clear();      //Clears the Anagram list before processing a new rack
                System.out.print("Rack?");
                Scanner in = new Scanner(System.in);
                String word = in.next();
                if(!word.equals(".")) {
                    word = word.replaceAll("[^a-zA-Z]", "");  //Removes all non letter characters from
                    Rack scrabble = new Rack(word);                           //the word
                    scrabbleScores = new ScoreTable();
                    ArrayList<String> wordSet = scrabble.getmultiset();

                    for (int i = 0; i < wordSet.size(); i++) {
                        addToAnagramList(wordSet.get(i), anagramList);
                    }

                    ArrayList<WordScore> annagramAndScores = getScrabbleWordScores(anagramList);
                    Collections.sort(annagramAndScores, new WordScoreComparator());

                    if(annagramAndScores.size()!=0) {
                        System.out.println("We can make " + annagramAndScores.size() + " words from " + "\"" + ad.getCanonicalForm(word) + "\"");
                        System.out.println("All of the words with their scores (sorted by score): ");
                        printList(annagramAndScores);
                    }
                    else System.out.println("We can make " + annagramAndScores.size() + " words from " + "\"" + ad.getCanonicalForm(word) + "\"");
                }
                else
                    sentinel= true;
            }
        }

        catch(FileNotFoundException exc){
            System.out.println("File not found");
        }
    }

    /**
     * Takes a word, finds it's anagrams, and then adds them to the general anagram list
     * @param s word to be from subset list
     * @param list anagram list for each rack
     */
    private static void addToAnagramList (String s, ArrayList<String> list){
        ArrayList<String> anagrams= ad.getAnagramsOf(s);
        list= anagramList;

        if (anagrams != null){
            for(int i=0; i<anagrams.size(); i++){
                String word= anagrams.get(i);
                if(word.length()!=0) {
                    list.add(word);
                }
            }
        }
    }

    /**
     * Method calculates the score of each word in the anagram list, and returns a new list of score, word pairs
     * @param list is a list of anagrams
     * @return new list containing score, word pairs
     */
    private static ArrayList<WordScore> getScrabbleWordScores (ArrayList<String> list){
        ArrayList<WordScore> anagramScoreList= new ArrayList<>();
        list= new ArrayList<>(anagramList);
        WordScore ws;

        for(int i=0; i<list.size(); i++){
            int val= scrabbleScores.calculateScore(list.get(i));
            ws= new WordScore(val, list.get(i));
            anagramScoreList.add(ws);
        }

        return new ArrayList<>(anagramScoreList);
    }

    /**
     * Prints out all the elements in a list
     * @param List containing score and word pairs of all the anagrams
     */
    private static void printList(ArrayList<WordScore> List){
        for (int i=0; i<List.size(); i++){
            System.out.println(List.get(i).getScore()+ ":" + List.get(i).getScrabbleWord());
        }

    }


}
