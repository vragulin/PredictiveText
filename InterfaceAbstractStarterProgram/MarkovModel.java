
/**
 * Generate text using a Markov chain based on the prior 4 letters
 * 
 * @author V. Ragulin
 * @version 25-<May-24
 */

import java.util.ArrayList;
import java.util.Random;

public class MarkovModel extends AbstractMarkovModel {
    
    public MarkovModel(int order) {
        super(order);
    }
    
    public String getRandomText(int numChars){
        if (myText == null) return "";
        StringBuilder sb = new StringBuilder();
        
        // Choose the first character randomly
        int index = myRandom.nextInt(myText.length()-order);
        String key = myText.substring(index, index+order);
        sb.append(key);
        
        // Subsequent based on a Markov chain
        String followsRandom = "";
        for (int k=0; k< numChars-order; k++){
            ArrayList<String> follows = getFollows(key);
            if (follows.size() > 0) {
        //        System.out.println("key " + key + " " + follows);
                followsRandom = follows.get(myRandom.nextInt(follows.size()));
            } else {
                followsRandom = String.valueOf(myText.charAt(myRandom.nextInt(myText.length())));
            }
            sb.append(followsRandom);
            key=sb.substring(sb.length()-order);
        }
        return sb.toString();
    }
    
}
