
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         FileResource file = new FileResource(filename);
         for(String s : file.lines()){
             //WebLogParser wp = new WebLogParser();
             LogEntry le = WebLogParser.parseEntry(s);
             //le = wp.parseEntry(s);
             records.add(le);
         }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     public int countUniqueIPs(){
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         for(LogEntry le : records){
             String IP = le.getIpAddress();
             if(!uniqueIPs.contains(IP)){
                 uniqueIPs.add(IP);
             }
         }
         return uniqueIPs.size();
     }
     public void printAllHigherThanNum(int num){
         for(LogEntry le : records){
             int statusCode = le.getStatusCode();
             if(statusCode > num){
                 System.out.println(le);
             }
         }
     }
     public ArrayList<String> uniqueIPVisitsOnDay(String someday){
         ArrayList<String> uniqueIPsVisitsOnDay = new ArrayList<String>();
         for(LogEntry le : records){
             String IP = le.getIpAddress();
             String dateString = le.getAccessTime().toString();
             dateString = dateString.substring(4,10);
             if(!uniqueIPsVisitsOnDay.contains(IP)&&dateString.equals(someday)){
                 uniqueIPsVisitsOnDay.add(IP);
             }
         }
         return uniqueIPsVisitsOnDay;
     }
     public int countUniqueIPsInRange(int low, int high){
         ArrayList<String> uniqueIPsInRange = new ArrayList<String>();
         for(LogEntry le : records){
             int statusCode = le.getStatusCode();
             String IP = le.getIpAddress();
             if(!uniqueIPsInRange.contains(IP)&&statusCode >= low&&statusCode <= high){
                 uniqueIPsInRange.add(IP);
                 //System.out.println(IP);
             }
         }
         return uniqueIPsInRange.size();
     }
     public HashMap<String,Integer> countVisitsPerIP(){
         HashMap<String,Integer> counts = new HashMap<String,Integer>();
         for(LogEntry le : records){
             String IP = le.getIpAddress();
             if(counts.containsKey(IP)){
                 int currentValue = counts.get(IP);
                 counts.put(IP,currentValue+1);
                 //System.out.println(IP);
             }else{
                 counts.put(IP,1);
             }
         }
         return counts;
     }
     public int mostNumberVisitsByIP(HashMap<String,Integer> counts){
         int currentMax = 0;
         for(String s : counts.keySet()){
             if(counts.get(s)>currentMax){
                 currentMax = counts.get(s);
             }
         }
         return currentMax;
     }
     public ArrayList<String> iPsMostVisits(HashMap<String, Integer> counts){
         ArrayList<String> mostVisits = new ArrayList<String>();
         int maxNumber = mostNumberVisitsByIP(counts);
         for(String s : counts.keySet()){
             if(counts.get(s)==maxNumber){
                 mostVisits.add(s);
             }
         }
         return mostVisits;
     }
     public HashMap<String, ArrayList<String>> iPsForDays(){
         HashMap<String, ArrayList<String>> iPsForDay = new HashMap<String, ArrayList<String>>();
         for(LogEntry le : records){
             String IP = le.getIpAddress();
             String dateString = le.getAccessTime().toString();
             dateString = dateString.substring(4,10);
             if(!iPsForDay.containsKey(dateString)){
                ArrayList<String> iPsVisitsOnDay = new ArrayList<String>();
                iPsVisitsOnDay.add(IP);
                iPsForDay.put(dateString,iPsVisitsOnDay);
             }else{
                 
                 iPsForDay.get(dateString).add(IP);
     
             }
         }
         return iPsForDay;
     }
     public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> iPsForDay){
         int mostVisits = 0;
         String day = "Nothing";
         for(String s: iPsForDay.keySet()){
             if(iPsForDay.get(s).size()>mostVisits){
                 mostVisits = iPsForDay.get(s).size();
                 day = s;
             }
         }
         return day;
     }
     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> iPsForDay, String day){
         //int mostVisits = 0;
         ArrayList<String> mostVisitsIPs = new ArrayList<String>();
         for(String s: iPsForDay.keySet()){
             if(s.equals(day)){
             ArrayList<String> IpsOnDay = iPsForDay.get(s);
             HashMap<String,Integer> count = new HashMap<String,Integer>();
             for(int k=0;k<IpsOnDay.size();k++ ){
                 String IP = IpsOnDay.get(k);
                 if(count.containsKey(IP)){
                     int currentValue = count.get(IP);
                     count.put(IP,currentValue+1);
                 }else{
                     count.put(IP,1);
                 }
             }
             int mostNum = mostNumberVisitsByIP(count);
             for(String ss: count.keySet()){
                 if(count.get(ss) == mostNum){
                     mostVisitsIPs.add(ss);
                 }
             }
             }
         }
         return mostVisitsIPs;
     }
}
