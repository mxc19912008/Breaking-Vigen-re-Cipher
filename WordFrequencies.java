
/**
 * Write a description of WordFrequencies here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.ArrayList;

public class WordFrequencies {
    private ArrayList<Integer> myFreqs;
    private ArrayList<String> myWords;
    
    public WordFrequencies(){
        myFreqs = new ArrayList<Integer>();
        myWords = new ArrayList<String>();
    }
    public void findUnique(){
        myFreqs.clear();
        myWords.clear();
        FileResource file = new FileResource();
        for(String s: file.words()){
            s = s.toLowerCase();
            int index = myWords.indexOf(s);
            if(index == -1){
                myWords.add(s);
                myFreqs.add(1);
            }else{
                int value = myFreqs.get(index);
                myFreqs.set(index,value+1);
            }
        }
    }
    public int findIndexOfMax(){
        int currentMax = myFreqs.get(0);
        int maxIndex = 0;
        for(int k=0;k<myFreqs.size();k++){
            if(myFreqs.get(k) > currentMax){
                currentMax = myFreqs.get(k);
                maxIndex = k;
            }
        }
        return maxIndex;
    }
    public void tester(){
        findUnique();
        for(int k=0;k<myWords.size();k++){
            System.out.println(myWords.get(k)+" "+"repeated "+myFreqs.get(k)+" times.");
        }
        int mostFrequentIndex = findIndexOfMax();
        System.out.println("Number of unique words: "+myWords.size());
        System.out.println("The most frequent word is "+myWords.get(mostFrequentIndex)+" and it occurred "+myFreqs.get(mostFrequentIndex)+" times");
    }
    
}
