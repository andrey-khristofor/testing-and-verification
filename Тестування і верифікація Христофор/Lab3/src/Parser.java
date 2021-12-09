import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/*
main working class of the program
responsible for parsing the text
*/
public class Parser {
    private String filePath;
    private String text = null;

    //main constructor
    public Parser(String path){
        filePath = path;
        try{
            text = readFile(path, Charset.defaultCharset());
        }
        catch (IOException e){
            System.out.println("IO Problem Occured");
        }
    }

    private String readFile(String path, Charset encoding)
            throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    /*Method which gives the output to the user
     * (without displaying it)*/
    public ArrayList getResult(){
        ArrayList<String> words = new Splitter(text).split();
        ArrayList filteredWords = filterResult(words);
        return filteredWords;
    }

    private boolean isLetter(char c){
        return Character.isLetter(c);
    }

    private boolean isVovel(char c){
        return ("AEIOUY".indexOf(Character.toUpperCase(c))!=-1);
    }

    private boolean isConsonant(char c){
        if(!isLetter(c))
            return  false;
        return !isVovel(c);
    }

    /*Get's a list of words from text as input.
     * Then it returns a list of those and only those
     * words, which have the longest subsequences of consonants*/
    private ArrayList filterResult(ArrayList<String> words){
        ArrayList fw = new ArrayList();
        if(words.isEmpty())
            return fw;
        int maxCons = 0;//maximal number of consonants
        int curCount;//num of consonants in this group
        boolean isInGroup;//indicates if we are in group of consonants
        int charnum;

        for(String w : words){
            curCount = 0;
            isInGroup  = false;
            charnum = 0;
            for(char c : w.toCharArray()){
                if(isConsonant(c)){
                    isInGroup = true;
                    curCount++;
                    if(charnum==(w.length()-1)){
                        PushToListObject values = pushToList(curCount, maxCons, fw, w);
                        curCount = values.curCount;
                        maxCons = values.maxCons;
                    }
                }
                else{
                    if(isInGroup){
                        PushToListObject values = pushToList(curCount, maxCons, fw, w);
                        curCount = values.curCount;
                        maxCons = values.maxCons;

                        curCount = 0;
                        isInGroup  =false;

                    }

                }
                charnum++;
            }
        }
        return fw;
    }

    private PushToListObject pushToList(int curCount, int maxCons, ArrayList fw, String w){
        if(curCount == maxCons){
            if(!fw.contains(w))
                fw.add(w);
        }else if(curCount > maxCons){
            fw.clear();

            if(!fw.contains(w))
                fw.add(w);
            maxCons = curCount;
        }
        return  new PushToListObject(curCount, maxCons);
    }

    private class PushToListObject{
        PushToListObject(int c, int m){
            this.curCount = c;
            this.maxCons = m;
        }
        public int curCount;
        public int maxCons;
    }


}