
/**
 * Write a description of GladlibDebug here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;

public class GladLibDebug {
	private HashMap<String, ArrayList<String>> myMap;
	private Random myRandom;
	//private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
	private static String dataSourceDirectory = "data";
	private ArrayList<String> wordTrack;
	
	public GladLibDebug(){
	    myMap = new HashMap<String, ArrayList<String>> ();
		initializeFromSource(dataSourceDirectory);
		
		myRandom = new Random();
		wordTrack = new ArrayList<String>();
	}
	private void initializeFromSource(String source) {
	    String[] category= {"adjective", "noun", "color", "country",
	                        "name", "animal", "timeframe", "verb", "fruit"};
	    for(int i=0;i<category.length;i++){
	        String currentString = "/"+category[i]+".txt";
	        ArrayList<String> currentList = readIt(source+currentString);
	        myMap.put(category[i],currentList);
	       }	
	}
	private String randomFrom(ArrayList<String> source){
		int index = myRandom.nextInt(source.size());
		return source.get(index);
	}
	
	private String getSubstitute(String label) {
	    return randomFrom(myMap.get(label));
		//return "**UNKNOWN**";
	}
	private String processWord(String w){
		int first = w.indexOf("<");
		int last = w.indexOf(">",first);
		if (first == -1 || last == -1){
			return w;
		}
		String prefix = w.substring(0,first);
		String suffix = w.substring(last+1);
		String sub = getSubstitute(w.substring(first+1,last));
		while(wordTrack.indexOf(sub)!=-1){
		    sub = getSubstitute(w.substring(first+1,last));
		}
		wordTrack.add(sub);
		return prefix+sub+suffix;
	}
	private String fromTemplate(String source){
		String story = "";
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		return story;
	}
	public void testfromTemplate(){
	    System.out.println(fromTemplate("data/num0_madtemplate.txt"));
	   }
	private ArrayList<String> readIt(String source){
		ArrayList<String> list = new ArrayList<String>();
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		return list;
	}
}
