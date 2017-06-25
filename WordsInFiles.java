
/**
 * Write a description of WordsInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import java.io.File;
public class WordsInFiles {
    private HashMap<String, ArrayList<String>> map;
    public WordsInFiles(){
        map = new HashMap<String, ArrayList<String>>();
    }
    public void addWordsFromFile(File f){
        FileResource file = new FileResource(f);
        for(String s: file.words()){
            if(map.containsKey(s)){
                ArrayList<String> sList = map.get(s);
                sList.add(f.getName());
            }else{
                ArrayList<String> sList = new ArrayList<String>();
                sList.add(f.getName());
                map.put(s,sList);
            }
        }
    }
    public void buildWordFileMap(){
        map.clear();
        DirectoryResource dir = new DirectoryResource();
        for(File f: dir.selectedFiles()){
            addWordsFromFile(f);
        }
    }
    public int maxNumber(){
        int max = 0;
        for(String s: map.keySet()){
            int current = map.get(s).size();
            if(current >max){
                max = current;
            }
        }
        return max;
    }
    public ArrayList<String> wordsInNumFiles(int number){
        ArrayList<String> wds = new ArrayList<String>();
        for (String s: map.keySet()){
            if(map.get(s).size() == number){
                wds.add(s);
            }
        }
        return wds;
    }
    public void printFilesIn(String word){
        for (String s: map.keySet()){
            if(s.equals(word)){
               ArrayList<String> wd = map.get(s);
               for(int k=0;k<wd.size();k++){
                    System.out.println(wd.get(k));
               }
            }
        }
    }
    public void tester(){
        buildWordFileMap();
        int maxNum = maxNumber();
        int currentMax = 0;
        //System.out.println(maxWord+" "+ currentMax);
        //System.out.println(map.size());
        //ArrayList<String> words = wordsInNumFiles(4);
        //System.out.println(words.size());
        printFilesIn("tree");
        //for(int k=0;k<words.size();k++){
            //System.out.println(words.get(k));
       // }
       // System.out.println(maxNum);
        //ArrayList<String> words = wordsInNumFiles(maxNum);
       // System.out.println(words.size());
        //for(int k=0;k<words.size();k++){
            //System.out.println(words.get(k));
        //}
       // for(int k=0;k<words.size();k++){
        //    printFilesIn(words.get(k));
       // }
    }
}
