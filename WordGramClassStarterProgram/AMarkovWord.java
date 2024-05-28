
/**
 * Abstract Class implementing predictive text based on a N-Word Markov logic
 * 
 * @author V. Ragulin
 * @version 28-May-24
 */

import java.util.Random;

public abstract class AMarkovWord implements IMarkovModel {
        protected String[] myText;
        protected Random myRandom;
        protected int order;
        
        public AMarkovWord(int order) {
            myRandom = new Random();
            this.order = order;
        }
        
        public void setRandom(int seed) {
            myRandom = new Random(seed);
        }
        
        public void setTraining(String text){
            myText = text.split("\\s+");
    	}
        
        public void append(StringBuilder sb, String word) {
            sb.append(word);
            sb.append(" ");
        }
        
        public abstract String getRandomText(int numChars);
        
        public String toString() {
        return "Markov Model order " + order;
    }
}
