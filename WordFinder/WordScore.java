// Name: Uche Uba
// USC NetID: uuba
// CS 455 PA4
// Spring 2018

/**
 * A class that takes a scrabble word, and it's associated value, and turns them into a pair
 */
public class WordScore {
    String word;
    int val;

    public WordScore(int val, String word){
        this.val= val;
        this.word= word;
    }

    public int getScore(){
        return val;
    }

    public String getScrabbleWord(){
        return word;
    }

    public void setScore(int x){
        val= x;
    }

    public void setWord(String anagram){
        word= anagram;
    }
}
