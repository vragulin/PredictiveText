
/**
 * Write a description of MarkovOne here.
 * 
 * @author V. Ragulin
 * @version 25-<May-24
 */

import java.util.ArrayList;
import java.util.Random;

public class MarkovTwo {
    private String myText;
    private Random myRandom;
    
    public MarkovTwo() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
    }
    
    public ArrayList<String> getFollows(String key) {
        
        ArrayList<String> follows = new ArrayList<String>();
        
        for (int i=0; i<myText.length()-2; i++){
            if (key.equals(myText.substring(i, i+2)))
                follows.add(myText.substring(i+2, i+3));
        }
        
        return follows;
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
