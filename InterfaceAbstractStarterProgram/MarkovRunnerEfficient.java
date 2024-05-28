
/**
 * Run and test the Efficient Text Generator with a HashMap
 * 
 * @author V. Ragulin - 26-May-2024
 * @version 1.0
 */

import java.text.DecimalFormat;
import edu.duke.*; 
import edu.duke.FileResource;

public class MarkovRunnerEfficient {
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setTraining(text);
        System.out.println("running with " + markov);
        for(int k=0; k < 3; k++){
            markov.setRandom(seed);
            String st= markov.getRandomText(size);
            printOut(st);
        }
    }
    
    public void runMarkov() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 200;
        int seed = 10;
        
        MarkovZero mz = new MarkovZero();
        runModel(mz, st, size, seed);
    
        MarkovOne mOne = new MarkovOne();
        runModel(mOne, st, size, seed);
        
        MarkovModel mThree = new MarkovModel(3);
        runModel(mThree, st, size, seed);
        
        MarkovFour mFour = new MarkovFour();
        runModel(mFour, st, size, seed);

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
    
    public void testHashMap() {
        EfficientMarkovModel em = new EfficientMarkovModel(2);
        String st = "yes-this-is-a-thin-pretty-pink-thistle";
        runModel(em, st, 50, 42);
        em.printHashMapInfo();
    }
    
    public void testInfo() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        
        EfficientMarkovModel em = new EfficientMarkovModel(5);
        runModel(em, st, 50, 531);
        em.printHashMapInfo();    
    }
        
    public void compareModels() {
        System.out.println("Running model comparison...");
        int order = 2;
        int seed = 42;
        int size = 1000;
        int times = 3;
        
        MarkovModel basic = new MarkovModel(order);
        EfficientMarkovModel efficient = new EfficientMarkovModel(order);
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        
        basic.setRandom(seed);
        efficient.setRandom(seed);
        
        // Test basic model
        long basicTime = System.nanoTime();
        basic.setTraining(st);
        runModelTimes(basic, times, size);
        basicTime = System.nanoTime() - basicTime;
        
        // Test efficient model
        long effTime = System.nanoTime();
        efficient.setTraining(st);
        runModelTimes(efficient, times, size);
        effTime = System.nanoTime() - effTime;
        
        DecimalFormat timeFormat = new DecimalFormat("#.##########");
        
        System.out.printf("Basic model, time:\t%s\n", timeFormat.format(basicTime / 1000000000.0));
        System.out.printf("Efficient model, time:\t%s\n", timeFormat.format(effTime / 1000000000.0));
    }
    
    private void runModelTimes(IMarkovModel markov, int times, int size) {
        for (int i=0; i<times; i++) 
            markov.getRandomText(size);
    }
}
