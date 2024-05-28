
/**
 * Write a description of MarkovOne here.
 * 
 * @author V. Ragulin
 * @version 25-<May-24
 */

import java.util.ArrayList;
import java.util.Random;

public class MarkovTwo extends AbstractMarkovModel {
    
    public MarkovTwo() {
        super(2);
    }
       
    public String getRandomText(int numChars){
        if (myText == null) return "";
        StringBuilder sb = new StringBuilder();
        
        // Choose the first character randomly
        int index = myRandom.nextInt(myText.length()-2);
        String key = myText.substring(index, index+2);
        sb.append(key);
        
        // Subsequent based on a Markov chain
        for (int k=0; k< numChars-2; k++){
            ArrayList<String> follows = getFollows(key);
            String followsRandom = "";
            if (follows.size() > 0) {
                System.out.println("key " + key + " " + follows);
                followsRandom = follows.get(myRandom.nextInt(follows.size()));
            } else {
                followsRandom = String.valueOf(myText.charAt(myRandom.nextInt(myText.length())));
            }
            sb.append(followsRandom);
            key=sb.substring(sb.length()-2);
        }
        return sb.toString();
    }
    
}
