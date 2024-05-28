
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.ArrayList;
import edu.duke.*;

public class Tester {

    private String getText() {
        FileResource fr = new FileResource("data/confucius.txt");
        String st = fr.asString();
        st = st.replace('\n', ' ');
        return st;
    }
        
        private void testGetFollows() {
            int order = 1;
            MarkovOne markov = new MarkovOne();
            System.out.println("Markov model order: 1.");
            String st = "this is a test yes this is a test.";
            System.out.println(st);
            markov.setRandom(150);
            markov.setTraining(st);
            
            for (int i=0; i<st.length()-order; i++){
                String key = st.substring(i, i+order);
                ArrayList<String> follows = markov.getFollows(key);
                System.out.printf("%s\t%s\n", key, follows);
            }
        }
        
        private void testGetFollowsWithFile(){
            MarkovTwo markov = new MarkovTwo();
            
            String st = getText();
            
            System.out.println(st);
            //markov.setRandom(150);
            markov.setTraining(st);
            int tcnt = markov.getFollows("he").size();
            System.out.println("Size: " + tcnt);
            
        }
        
        public static void main() {
            Tester t = new Tester();
            //t.testGetFollows();
            t.testGetFollowsWithFile();
        }        
}
