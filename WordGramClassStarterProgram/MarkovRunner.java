
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.text.DecimalFormat;

public class MarkovRunner {
    public void runModel(IMarkovModel markov, String text, int size){ 
        markov.setTraining(text); 
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runModel(IMarkovModel markov, String text, int size, int seed){ 
        markov.setTraining(text); 
        markov.setRandom(seed);
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runMarkov() { 
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        //MarkovWordOne markovWord = new MarkovWordOne(); 
        //runModel(markovWord, st, 200); 
        MarkovWord markov = new MarkovWord(5);
        runModel(markov, st, 120, 844);
    } 

    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println(); 
                psize = 0;
            } 
        } 
        System.out.println("\n----------------------------------");
    } 

    public void testHashMap(){
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' ');  
        //String st = "this is a test yes this is really a test yes a test this is wow";
        EfficientMarkovWord markov = new EfficientMarkovWord(2);
        runModel(markov, st, 50, 65); 
        markov.printHashMapInfo();        
    }
    
    public void compareModels() {
        System.out.println("Running model comparison...");
        
        int order = 2;
        int seed = 42;
        int size = 100;
        
        MarkovWord basic = new MarkovWord(order);
        EfficientMarkovWord efficient = new EfficientMarkovWord(order);
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        
        // Test basic model
        long basicTime = System.nanoTime();
        runModel(basic, st, size, seed);
        basicTime = System.nanoTime() - basicTime;
        
        // Test efficient model
        long effTime = System.nanoTime();
        runModel(efficient, st, size, seed);
        effTime = System.nanoTime() - effTime;
        
        DecimalFormat timeFormat = new DecimalFormat("#.##########");
        
        
        System.out.printf("Basic model, time:\t%s\n"
                , timeFormat.format(basicTime/ 1000000000.0));
        System.out.printf("Efficient model, time:\t%s\n"
                , timeFormat.format(effTime/ 1000000000.0));
    
    }
}
