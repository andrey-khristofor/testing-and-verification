import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Parser {

    public void mainFunc() {
        File fl = new File("D:/Бурса/Тестування і верифікація Христофор/test.txt");
        String Delemiters = "\" ( ) , + - = < > ? ! @ # $ % ^ & * ~ ' . / \\";
        String[] DelemitersArray = Delemiters.split(" ");
        Map<Double, Set<String>> map = new HashMap<>();
        try {
            Scanner sc = new Scanner(fl);
            while (sc.hasNext()) {
                String CurrentStr = sc.nextLine();
                for (String s : DelemitersArray) {CurrentStr = CurrentStr.replace(s.charAt(0), ' ');}
                String[] CurrentParsed = ReplaceAndParse(CurrentStr);
                CurrentParsed = ChangeLongWords(CurrentParsed);
                for (String s : CurrentParsed) {
                    double partition = PartitionCount(s);
                    partition = partition / s.length();
                    Set<Double> keys = map.keySet();
                    if (keys.contains(partition)) {
                        Set<String> currentSet = map.get(partition);
                        currentSet.add(s);
                        map.replace(partition, currentSet);
                    } else {
                        Set<String> currSet = new HashSet<>();
                        currSet.add(s);
                        map.put(partition, currSet);}}}
            sc.close();
            String result = SortAndOut(map);
            System.out.print(result);
        } catch (FileNotFoundException e) {e.printStackTrace();}}


    public String SortAndOut(Map<Double, Set<String>> map) {
        String res = "";
        Set<Double> keys = map.keySet();
        List<Double> sortedList = new ArrayList<>(keys);
        Collections.sort(sortedList);

        for (double p : sortedList) {
            Set<String> currSet = map.get(p);
            for (String s : currSet) {
                res += p;
                res += " ";
                res += s;
                res += "\n";
            }
        }

        return res;
    }


    public String[] ReplaceAndParse(String CurrentStr) {
        String CurrentStrWithSpacesOnly = CurrentStr.trim().replaceAll(" +", " ");
        String[] CurrentParsed = CurrentStrWithSpacesOnly.split(" ");

        return CurrentParsed;
    }


    public String[] ChangeLongWords(String[] CurrentParsed) {
        for (int i = 0; i < CurrentParsed.length; i++) {
            if (CurrentParsed[i].length() > 30) {
                CurrentParsed[i] = CurrentParsed[i].substring(0, 30);
            }
        }
        return CurrentParsed;
    }


    public double PartitionCount(String s)
    {
        double partition = 0;
        for (int j = 0; j < s.length(); j++) {
            char c = s.toUpperCase().charAt(j);
            if (c == 'A' || c == 'O' || c == 'E' || c == 'U' || c == 'Y' || c == 'I') {
                partition++;
            }
        }

        return partition/s.length();
    }


}