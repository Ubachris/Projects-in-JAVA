// Name: Uche Uba
// USC NetID: uuba
// CS 455 PA4
// Spring 2018

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Arrays;

/**
 * A Rack of Scrabble tiles. This class takes letters, sorts them, and returns a list of its subsets
 */

public class Rack {
    private String word;
   
    public Rack(String word){
        this.word= word;
    }

    /**
     * This method returns all subsets of a word, by using a recursive helper function.
     * It finds the uniques characters in a word, and the occurence of the characters, and
     * passes them as parameers into the helper function
     * @return a List of all subsets of letters on a rack
     */
    public ArrayList<String> getmultiset(){
        HashSet<Character> uniqueChar = new HashSet<>();    //Uses a hash set to ensure the set contains only unique
                                                           //characters
        for (int i=0; i<word.length(); i++){
            if(!uniqueChar.contains(word.charAt(i)))
                uniqueChar.add(word.charAt(i));
        }

        Character[] uniqueWord= new Character[uniqueChar.size()];
        uniqueChar.toArray(uniqueWord);
        Arrays.sort(uniqueWord);

        String unique = "";                             //Turns the unique character into a word
        for(Character c: uniqueWord){
            unique += c.toString();
        }

        int [] multiplicity= new int[unique.length()];

        char [] wordArray= word.toCharArray();
        Arrays.sort(wordArray);                       //Sorts the array containing unique characters from the rack

        int count= 1;
        int counter= 0;
        for(int i=1; i< wordArray.length; i++){           //Counts the frequency of characters  on a rack
            char temp= wordArray[i-1];

            if(temp == wordArray[i]){
                count++;
            }

            else {
                multiplicity[counter] = count;
                counter++;
                count = 1;
            }

            if(i== wordArray.length-1)
                multiplicity[counter]= count;
        }

        return allSubsets(unique, multiplicity, 0);     // Recursively finds subsets of the whole word

    }


   /**
    * Finds all subsets of the multiset starting at position k in unique and mult.
    * unique and mult describe a multiset such that mult[i] is the multiplicity of the char
    *      unique.charAt(i).
    * PRE: mult.length must be at least as big as unique.length()
    *      0 <= k <= unique.length()
    * @param unique a string of unique letters
    * @param mult the multiplicity of each letter from unique.  
    * @param k the smallest index of unique and mult to consider.
    * @return all subsets of the indicated multiset
    * @author Claire Bono
    */
   private static ArrayList<String> allSubsets(String unique, int[] mult, int k) {
      ArrayList<String> allCombos = new ArrayList<>();
      
      if (k == unique.length()) {  // multiset is empty
         allCombos.add("");
         return allCombos;
      }
      
      // get all subsets of the multiset without the first unique char
      ArrayList<String> restCombos = allSubsets(unique, mult, k+1);
      
      // prepend all possible numbers of the first char (i.e., the one at position k) 
      // to the front of each string in restCombos.  Suppose that char is 'a'...
      
      String firstPart = "";          // in outer loop firstPart takes on the values: "", "a", "aa", ...
      for (int n = 0; n <= mult[k]; n++) {   
         for (int i = 0; i < restCombos.size(); i++) {  // for each of the subsets 
                                                        // we found in the recursive call
            // create and add a new string with n 'a's in front of that subset
            allCombos.add(firstPart + restCombos.get(i));  
         }
         firstPart += unique.charAt(k);  // append another instance of 'a' to the first part
      }
      
      return allCombos;
   }

   
}
