import java.util.ArrayList;

/*Very useful class for
 * splitting English text into separate words
 * It also works properly with - and ' in
 * words*/

public class Splitter {
    private String text;

    public  Splitter(){
        text="";
    }

    public Splitter(String text){
        this.text = text;
    }
    public ArrayList<String> split(){
        ArrayList<String> words = new ArrayList<String>();
        StringBuilder currentWord = new StringBuilder();
        boolean isInWord = false;
        int index = -1;
        char[] chars = text.toCharArray();
        for(char c : chars){
            index++;

            if(isLetter(c)){
                if(!isInWord)
                    isInWord = true;
                currentWord.append(c);
            }
            else if(!isLetter(c) && !isSuspicious(c) && isInWord){
                isInWord = false;
                if(!currentWord.toString().equals("")) {
                    words.add(currentWord.toString());
                    currentWord.setLength(0);
                }
            }else if(isSuspicious(c)){
                if(isInWord && isLetter(chars[index+1])){
                    currentWord.append(c);
                }
                else {
                    if(!currentWord.toString().equals("")) {
                        words.add(currentWord.toString());
                        currentWord.setLength(0);
                    }
                    isInWord = false;
                }
            }
            else{
                isInWord = false;
            }


        }

        if(!currentWord.toString().equals("")) {
            words.add(currentWord.toString());
            currentWord.setLength(0);
        }
        return words;
    }

    public boolean isLetter(char c){
        return Character.isLetter(c);
    }

    private boolean isSuspicious(char c){
        return (c=='-'||c=='\'');
    }

    public void setText(String text){
        this.text = text;
    }
}