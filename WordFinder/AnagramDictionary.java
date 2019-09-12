// Name: Uche Uba
// USC NetID: uuba
// CS 455 PA4
// Spring 2018

import java.io.*;
import java.util.*;
import java. lang.*;



/**
 * A dictionary of all anagram sets. 
 * Note: the processing is case-sensitive; so if the dictionary has all lower
 * case words, you will likely want any string you test to have all lower case
 * letters too, and likewise if the dictionary words are all upper case.
 */

public class AnagramDictionary {

    private Map<String, ArrayList<String>> dictionaryAnagrams;

    /**
     * Create an anagram dictionary from the list of words given in the file
     * indicated by fileName.
     * PRE: The strings in the file are unique.
     *
     * @param fileName the name of the file to read from
     * @throws FileNotFoundException if the file is not found
     */
    public AnagramDictionary(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner in = new Scanner(file);
        ArrayList<String> Anagrams;
        dictionaryAnagrams = new HashMap<>();


        while (in.hasNextLine()) {
            String word = in.nextLine();
            String sorted = getCanonicalForm(word);
            Anagrams = dictionaryAnagrams.get(sorted);

            if (Anagrams == null) {
                Anagrams = new ArrayList<>();
                Anagrams.add(word);
                dictionaryAnagrams.put(sorted, Anagrams);

            } else {
                Anagrams.add(word);
            }
        }
    }

    /**
     * This method turns a string into a character array, sorts it then returns it as a string
     * @param word to be sorted
     * @return string retutned
     */
    public String getCanonicalForm(String word){
       char[] tempArray= word.toCharArray();
       Arrays.sort(tempArray);

       return new String(tempArray);
    }
   

   /**
    * Get all anagrams of the given string. This method is case-sensitive.
    * E.g. "CARE" and "race" would not be recognized as anagrams.
    * @param s string to process
    * @return a list of the anagrams of s
    * 
    */
   public ArrayList<String> getAnagramsOf(String s) {
       String temp = getCanonicalForm(s);
       ArrayList<String> result= new ArrayList<>();

       if(dictionaryAnagrams.containsKey(temp)){
           result= dictionaryAnagrams.get(temp);

       }

       return result;
   }
   
   
}
