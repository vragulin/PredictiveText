
/**
 * Write a description of MarkovOne here.
 * 
 * @author V. Ragulin
 * @version 25-<May-24
 */

import java.util.ArrayList;
import java.util.Random;

public class MarkovOne extends AbstractMarkovModel {
    
    public MarkovOne() {
        super(1);
    }
        
    public String getRandomText(int numChars){
        if (myText == null) return "";
        StringBuilder sb = new StringBuilder();
        
        // Choose the first character randomly
        int index = myRandom.nextInt(myText.length()-1);
        String key = myText.substring(index, index+1);
        sb.append(key);
        
        // Subsequent based on a Markov chain
        for (int k=0; k< numChars-1; k++){
            ArrayList<String> follows = getFollows(key);
            String followsRandom = follows.get(myRandom.nextInt(follows.size()));
            sb.append(followsRandom);
            key=followsRandom;
        }
        return sb.toString();
    }
    
}
