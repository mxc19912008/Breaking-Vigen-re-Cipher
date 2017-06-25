
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog3-short_log");
        la.printAll();
    }
    public void testUniqueIP(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        System.out.println(la.countUniqueIPs());
    }
    public void testPrintAllHigherThanNum(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        la.printAllHigherThanNum(400);
    }
    public void testUniqueIPVisitsOnDay(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        ArrayList<String> sep14 = la.uniqueIPVisitsOnDay("Sep 24");
        System.out.println("Sep 24 size: " +sep14.size());
        for(int k=0;k<sep14.size();k++){
            System.out.println("Mar 17 " +sep14.get(k));
        }
        //ArrayList<String> sep30 = la.uniqueIPVisitsOnDay("Sep 30");
        //for(int k=0;k<sep30.size();k++){
           // System.out.println("sep30 "+sep30.get(k));
        //}
    }
    public void testCountUniqueIPsInRange(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        System.out.println("400-499 status code "+la.countUniqueIPsInRange(400,499));
        //System.out.println("300-399 status code "+la.countUniqueIPsInRange(300,399));
    }
    public void testMostNumberVisitsByIP(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String,Integer> counts = la.countVisitsPerIP();
        int most = la.mostNumberVisitsByIP(counts);
        System.out.println("Most Number Visits By IP is "+most);
    }
    public void testiPsMostVisits(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String,Integer> counts = la.countVisitsPerIP();
        ArrayList<String> most = la.iPsMostVisits(counts);
        for(int k=0;k<most.size();k++){
            System.out.println(most.get(k));
        }
    }
    public void testIPsForDays(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String, ArrayList<String>> map = la.iPsForDays();
        for(String s: map.keySet()){
            for(int k=0;k<map.get(s).size();k++){
                System.out.println(s+":"+map.get(s).get(k));
            }
        }
    }
    public void testdayWithMostIPVisits(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String, ArrayList<String>> map = la.iPsForDays();
        System.out.println(la.dayWithMostIPVisits(map));
    }
    public void testiPsWithMostVisitsOnDay(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String, ArrayList<String>> map = la.iPsForDays();
        ArrayList<String> mostVisitsIPs = la.iPsWithMostVisitsOnDay(map,"Sep 29");
        for(int k=0;k<mostVisitsIPs.size();k++){
            System.out.println(mostVisitsIPs.get(k));
        }
    }
}
