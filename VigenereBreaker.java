import java.util.*;
import edu.duke.*;
import java.io.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        //REPLACE WITH YOUR CODE
        StringBuilder answer = new StringBuilder();
        for(int i=whichSlice;i<message.length();i+=totalSlices){
            answer.append(message.substring(i,i+1));
        }
        return answer.toString();
        //return "WRITE ME!";
    }
    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> dict = new HashSet<String>();
        for(String s : fr.lines()){
            s = s.toLowerCase();
            dict.add(s);
        }
        return dict;
    }
    public int countWords(String message,HashSet<String> dict){
        //Array words = message.split("\\W+");
        int count = 0;
        for(String s: message.split("\\W+")){
            s = s.toLowerCase();
            if(dict.contains(s)){
                count+=1;
            }
        }
        return count;
    }
    public String breakForLanguage(String encrypted,HashSet<String> dict){
        int maxCount = 0;
        String trueDecrypted = "nothing";
        int keyLength = 0;
        char mostCommonChar = mostCommonCharIn(dict);
        for(int i=1;i<=100;i++){
            int[] keys = tryKeyLength(encrypted,i,mostCommonChar);
            VigenereCipher vc = new VigenereCipher(keys);
            String decrypted = vc.decrypt(encrypted);
            int count = countWords(decrypted,dict);
            if(count>maxCount){
                maxCount = count;
                trueDecrypted = decrypted;
                keyLength = i;
            }
        }
        System.out.println("The key length used is "+ keyLength);
        System.out.println("The num of most meaningful words is "+ maxCount);
        return trueDecrypted;
    }
    public int[] tryKeyLength(String encrypted, int klength,char mostCommon) {
        int[] key = new int[klength];
        //StringBuilder encryptedSb = new StringBuilder(encrypted);
        for(int k =0;k<klength;k++){
            String slicedString = sliceString(encrypted,k,klength);
            CaesarCracker cc = new CaesarCracker(mostCommon);
            int onekey = cc.getKey(slicedString);
            key[k] = onekey;
        }
        //WRITE YOUR CODE HERE
        return key;
    }
    public char mostCommonCharIn(HashSet<String> dictionary){
        HashMap<Character,Integer> AlfCounts = new HashMap<Character,Integer>();
        for(String s :dictionary){
            StringBuilder sB = new StringBuilder(s);
            for(int i=0;i<s.length();i++){
                char currentChar = sB.charAt(i);
                if(AlfCounts.containsKey(currentChar)){
                    AlfCounts.put(currentChar,AlfCounts.get(currentChar)+1);
                }else{
                    AlfCounts.put(currentChar,1);
                }
            }
        }
        int currentMax = 0;
        Character resultchar =null;
        for(char c :AlfCounts.keySet()){
            if(AlfCounts.get(c)>currentMax){
                currentMax = AlfCounts.get(c);
                resultchar = c;
            }
        }
        return resultchar;
    }
    public void breakForAllLangs(String encrypted, HashMap<String,HashSet<String>> languages ){
        int countMax = 0;
        String theBestLang = "nothing"; 
        for(String s : languages.keySet()){
            HashSet<String> dict = languages.get(s);
            int count = countWords(encrypted ,dict);
            if(count > countMax){
                countMax = count;
                theBestLang = s;
            }
        }
        String decrypted = breakForLanguage(encrypted,languages.get(theBestLang));
        System.out.println("The language used is "+ theBestLang);
        System.out.println("The decrypted message for this language is :");
        System.out.println(decrypted);
    }
    public void breakVigenere () {
        FileResource fr = new FileResource();
        String frString = fr.asString();
        DirectoryResource dictDr = new DirectoryResource();
        HashMap<String,HashSet<String>> lang = new  HashMap<String,HashSet<String>>();
        System.out.println("Your program is making progress...");
        for(File f : dictDr.selectedFiles()){
            HashSet<String> dicts = new HashSet<String>();
            dicts.clear();
            FileResource frr = new FileResource(f);
            for(String s : frr.words()){
                dicts.add(s); 
            }
            lang.put(f.getName(),dicts);
        }
        //HashSet<String> dict = readDictionary(dictfr);
        //int klength = 4;
        //int[] keys = tryKeyLength(frString,klength ,'e');
        //VigenereCipher vc = new VigenereCipher(keys);
        breakForAllLangs(frString,lang);
        //String decrypted = vc.decrypt(frString);
        //System.out.print(decrypted);
        //WRITE YOUR CODE HERE
    }
    
    
}
