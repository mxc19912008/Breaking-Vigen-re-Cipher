import edu.duke.*;
import java.util.*;

public class GladLib {
	private HashMap<String, ArrayList<String>> myMap;
	private Random myRandom;
	private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
	private static String dataSourceDirectory = "data";
	private ArrayList<String> wordTrack;
	private ArrayList<String> categoriesUsed;
	
	public GladLib(){
	    myMap = new HashMap<String, ArrayList<String>> ();
		initializeFromSource(dataSourceDirectory);
		categoriesUsed = new ArrayList<String>(); 
		myRandom = new Random();
		wordTrack = new ArrayList<String>();
	}
	
	public GladLib(String source){
		initializeFromSource(source);
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
	    if (label.equals("number")){
			return ""+myRandom.nextInt(50)+5;
		}
	    return randomFrom(myMap.get(label));
		//return "**UNKNOWN**";
	}
	
	public String processWord(String w){
		int first = w.indexOf("<");
		int last = w.indexOf(">",first);
		if (first == -1 || last == -1){
			return w;
		}else{
		    String prefix = w.substring(0,first);
		    String suffix = w.substring(last+1);
		    if(!categoriesUsed.contains(w.substring(first+1,last))){
		        categoriesUsed.add(w.substring(first+1,last));
		    }
		    String sub = getSubstitute(w.substring(first+1,last));
		    while(wordTrack.indexOf(sub)!=-1){
		        sub = getSubstitute(w.substring(first+1,last));
		    }
		    wordTrack.add(sub);
		    return prefix+sub+suffix;
	    }
    }
    
	
	private void printOut(String s, int lineWidth){
		int charsWritten = 0;
		for(String w : s.split("\\s+")){
			if (charsWritten + w.length() > lineWidth){
				System.out.println();
				charsWritten = 0;
			}
			System.out.print(w+" ");
			charsWritten += w.length() + 1;
		}
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
	private int totalWordsInMap(){
	    int numOfWords = 0;
	    for(String s: myMap.keySet()){
	        numOfWords += myMap.get(s).size();
	       }
	    return numOfWords;
	}
	private int totalWordsConsidered(){
	    int wordsConsidered = 0;
	    for(int k=0;k<categoriesUsed.size();k++){
	        String currWord = categoriesUsed.get(k);
	        wordsConsidered += myMap.get(currWord).size();
	    }
	    return wordsConsidered;
	}
	public void makeStory(){
	    wordTrack.clear();
	    System.out.println("\n");
		String story = fromTemplate("data/madtemplate2.txt");
		printOut(story, 60);
		System.out.println("\n");
		System.out.println("Totally there are "+wordTrack.size()+" words replaced.");
		System.out.println("Totally there are "+totalWordsInMap()+" words to select from.");
		for(int k=0;k<categoriesUsed.size();k++){
            System.out.println(categoriesUsed.get(k));
        }
	}
	


}
