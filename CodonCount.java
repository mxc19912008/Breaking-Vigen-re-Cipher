
/**
 * Write a description of CodonCount here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
public class CodonCount {
    private HashMap<String,Integer> DnaCountMap;
    public CodonCount(){
        DnaCountMap = new HashMap<String,Integer>();
    }
    public void buildCodonMap(int start, String dna){
        DnaCountMap.clear();
        String readFramedDna = dna.substring(start);
        for(int i=0;i<=readFramedDna.length()-3;i=i+3){
                String s = readFramedDna.substring(i,i+3);
                if(DnaCountMap.containsKey(s)){
                    DnaCountMap.put(s,DnaCountMap.get(s)+1);
                }else{
                    DnaCountMap.put(s,1);
                }
        }
    }
    public String getMostCommonCodon(){
        int currentMax = 0;
        String result = "Nothing";
        for(String s : DnaCountMap.keySet()){
            if(DnaCountMap.get(s)>=currentMax){
                currentMax = DnaCountMap.get(s);
                result = s;
            }
        }
        return result;
    }
    public void printCodonCounts(int start,int end){
        for(String s : DnaCountMap.keySet()){
            if(DnaCountMap.get(s)>=start&&DnaCountMap.get(s)<=end){
                System.out.println("Counts of codons between "+start+" and "+end+" inclusive are: ");
                System.out.println(s+" "+DnaCountMap.get(s));
            }
        }
    }
    public void tester(){
        //FileResource file = new FileResource();
        //String dna = file.toString();
        String dna = "CAACCTTTAAAAGGAAGAAATCGCAGCAGCCCAGAACCAACTGCATAACATACAACCTTTAAAAGGAAGAAATCGCAGCAGCCCAGAACCAACTGCATAACATACAACCTTTAAAAGGAAGAAATCGCACCAGCCCAGAATCAACTGCATAACATACAAACTTTAAAAGGAAGAAATCTAACATACAACCTTTAAAAGGAAGAAATCGCACCAGCCCAGAATCAACTGCATAACATACAAACTTTAAAAGGAAGAAATCCAACCTTTAAAAGGAAGAAATCGCAGCAGCCCAGAACCAACTGCATAACATACAACCTTTAAAAGGAAGAAATCGCAGCAGCCCAGAACCAACTGCATAACATACAACCTTTAAAAGGAAGAAATCGCACCAGCCCAGAATCAACTGCATAACATACAAACTTTAAAAGGAAGAAATC";
        dna = dna.toUpperCase();
        //for(int i=0;i<=2;i++){
            //buildCodonMap(i,dna);
            //String s = getMostCommonCodon();
            //System.out.println("Reading frame starting with "+i+" results in "+DnaCountMap.size()+" unique codons");
            //System.out.println("and most common codon is "+s+" with count "+ DnaCountMap.get(s));
            //printCodonCounts(1,5);
        //}
        buildCodonMap(0,dna);
        //String s = getMostCommonCodon();
        //System.out.println("and most common codon is "+s+" with count "+ DnaCountMap.get(s));
        
        //System.out.println(DnaCountMap.size());
        for(String s:DnaCountMap.keySet()){
            if(DnaCountMap.get(s)==7){
                System.out.println(s+DnaCountMap.get(s));
            }
        }
    }
}
