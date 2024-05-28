
/**
 * Generate text using a Markov chain based on the prior 4 letters
 * 
 * @author V. Ragulin
 * @version 25-<May-24
 */

import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;

public class EfficientMarkovModel extends AbstractMarkovModel {
    private HashMap<String, ArrayList<String>> map;
    
    public EfficientMarkovModel(int order) {
        super(order);
        map = new HashMap<String, ArrayList<String>>();
    }
    
    /**
     * Get key or order-length starting on index.
     */
    private String getKey(int index) {
        return myText.substring(index, index + order);
    }
    
    private String getFollowingLetter(int index) {
        return myText.substring(index+order, index + order + 1);
    }
    
    @Override
    public void setTraining(String s) {
        super.setTraining(s);
        buildMap();
        printHashMapInfo();
    }
    
    /**
     * Scan the entire training text and build map for all possible keys
     */
    private void buildMap() {
        for (int i=0; i< myText.length() - order; i++){
            String key = getKey(i);
            String follow = getFollowingLetter(i);
            
            if (map.containsKey(key))
                map.get(key).add(follow);
            else {
                ArrayList<String> list = new ArrayList<String>();
                list.add(follow);
                map.put(key, list);
            }
        }
    }
    
    @Override
    public ArrayList<String> getFollows(String key) {
        return map.get(key);
    }
    
    public void printHashMapInfo() {
        System.out.printf("Map size:\t%d\nMax size:\t%d\n", mapSize(), longestFollowsSize());
        //for (String key : map.keySet()) {
        //    System.out.printf("Key:\t[%s]\tvalues: ", key);
        //    System.out.println(map.get(key));
        //}
    }
    
    public int mapSize(){
        return map.size() + 1;  // because we drop the final sequence
    }
    
    public int longestFollowsSize() {
        int maxSize = 0;
        
        for (String key : map.keySet())
            maxSize = Math.max(maxSize, map.get(key).size());
            
        return maxSize;
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

            if (follows == null)  break;

            followsRandom = follows.get(myRandom.nextInt(follows.size()));

            sb.append(followsRandom);
            key=sb.substring(sb.length()-order);
        }
        return sb.toString();
    }
    
    @Override
    public String toString() {
        return "Efficient Markov Model order " + order;
    }
}
