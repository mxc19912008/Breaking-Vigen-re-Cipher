
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.ArrayList;
public class CharactersInPlay {
    private ArrayList<String> characterName;
    private ArrayList<Integer> counts;
    public CharactersInPlay(){
        characterName = new ArrayList<String>();
        counts = new ArrayList<Integer>();
    }
    public void update(String person){
        int index = characterName.indexOf(person);
        if(index == -1){
            characterName.add(person);
            counts.add(1);
        }else{
            int value = counts.get(index);
            counts.set(index,value+1);
        }
    }
    public void findAllCharacters(){
        characterName.clear();
        counts.clear();
        FileResource file = new FileResource();
        for(String s: file.lines()){
            if(s.indexOf(".")!=-1){
                String characterName = s.substring(0,s.indexOf("."));
                update(characterName);
            }
        }
    }
    public void tester(){
        findAllCharacters();
        for(int k=0;k<characterName.size();k++){
            if(counts.get(k)>1){
                System.out.println(characterName.get(k)+" "+counts.get(k));
            }
        }
    }
    public void charactersWithNumParts(int num1, int num2){
        //assume num1 <= num2
        for(int k=0;k<characterName.size();k++){
            if(counts.get(k)>=num1&&counts.get(k)<=num2){
                System.out.println(characterName.get(k)+" "+counts.get(k));
            }
        }
    }
    public void testerOfNums(){
        findAllCharacters();
        charactersWithNumParts(10,15);
    }
}
