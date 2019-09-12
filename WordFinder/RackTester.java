import java.util.ArrayList;

public class RackTester {
    public static void main (String[] args){

        String word= "Hello";
        Rack scrabble = new Rack(word);
        ArrayList<String> words= scrabble.getmultiset();

        for(int i=0; i<words.size(); i++){
            System.out.println(words.get(i));
        }


    }
}
