
/**
 * Generate text using a Markov chain based on the prior 4 letters
 * 
 * @author V. Ragulin
 * @version 25-<May-24
 */

import java.util.ArrayList;
import java.util.Random;

public class MarkovFour implements IMarkovModel{
    private String myText;
    private Random myRandom;
    private int nKey;
    
    public MarkovFour() {
        myRandom = new Random();
        nKey = 4;
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
    }
    
    public ArrayList<String> getFollows(String key) {
        
        ArrayList<String> follows = new ArrayList<String>();
        
        for (int i=0; i<myText.length()-nKey; i++){
            if (key.equals(myText.substring(i, i+nKey)))
                follows.add(myText.substring(i+nKey, i+nKey+1));
        }
        
        return follows;
    }
    
    public String getRandomText(int numChars){
        if (myText == null) return "";
        StringBuilder sb = new StringBuilder();
        
        // Choose the first character randomly
        int index = myRandom.nextInt(myText.length()-nKey);
        String key = myText.substring(index, index+nKey);
        sb.append(key);
        
        // Subsequent based on a Markov chain
        String followsRandom = "";
        for (int k=0; k< numChars-nKey; k++){
            ArrayList<String> follows = getFollows(key);
            if (follows.size() > 0) {
        //        System.out.println("key " + key + " " + follows);
                followsRandom = follows.get(myRandom.nextInt(follows.size()));
            } else {
                followsRandom = String.valueOf(myText.charAt(myRandom.nextInt(myText.length())));
            }
            sb.append(followsRandom);
            key=sb.substring(sb.length()-nKey);
        }
        return sb.toString();
    }
    
}
